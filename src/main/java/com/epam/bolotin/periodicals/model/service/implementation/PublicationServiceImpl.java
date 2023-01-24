package com.epam.bolotin.periodicals.model.service.implementation;

import com.epam.bolotin.periodicals.exception.AppException;
import com.epam.bolotin.periodicals.model.Validator;
import com.epam.bolotin.periodicals.model.db.dto.PublicationDto;
import com.epam.bolotin.periodicals.model.db.entity.Publication;
import com.epam.bolotin.periodicals.model.db.repository.AccountRepository;
import com.epam.bolotin.periodicals.model.db.repository.PublicationRepository;
import com.epam.bolotin.periodicals.model.service.PublicationService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author: Viacheslav Bolotin
 * @date: 06.01.2023
 */
public class PublicationServiceImpl implements PublicationService {

    private final AccountRepository accountRepository;
    private final PublicationRepository publicationRepository;

    public PublicationServiceImpl(PublicationRepository publicationRepository, AccountRepository accountRepository) {
        this.publicationRepository = publicationRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public void save(Publication publication) throws AppException {
        publicationRepository.create(publication);
    }

    @Override
    public void update(Publication publication) throws AppException {
        publicationRepository.update(publication);
    }

    @Override
    public void delete(long id) throws AppException {
        publicationRepository.delete(id);
    }

    @Override
    public List<Publication> findAll() {
        return publicationRepository.getAll();
    }

    @Override
    public List<PublicationDto> findAllFullInfo() {
        return publicationRepository.getAllFullInfo();
    }

    @Override
    public Publication findByID(long id) {
        return publicationRepository.getById(id);
    }

    @Override
    public Publication findByTopicId(long id) {
        return publicationRepository.getByTopicId(id);
    }

    @Override
    public PublicationDto findFullInfoByID(long id) {
        return publicationRepository.getFullInfoById(id);
    }

    @Override
    public boolean validateAndFillPublication(Publication publication, HttpServletRequest request) {
        String errorMessage;

        String tempString = request.getParameter("publication_topic").trim();
        publication.setTopicId(Long.parseLong(tempString));

        tempString = request.getParameter("publication_title").trim();
        errorMessage = Validator.validateTitle(tempString, "publication_title",255);
        if (errorMessage != null) {
            request.setAttribute("errorMessage", errorMessage);
            return false;
        }
        publication.setTitle(tempString);

        tempString = request.getParameter("publication_price").trim();
        errorMessage = Validator.validateAmount(tempString);
        if (errorMessage != null) {
            request.setAttribute("errorMessage", errorMessage);
            return false;
        }
        publication.setPrice(new BigDecimal(tempString));

        tempString = request.getParameter("publication_text").trim();
        publication.setText(tempString);

        return true;
    }

    @Override
    public List<PublicationDto> findAllLimitSort(long from, long size, String sortType,
                                                 long topicFilter, String titleFilter, long userFilter){
        return publicationRepository.getAllLimitSort(from, size, sortType, topicFilter, titleFilter, userFilter);
    }

    @Override
    public long findSize(long topicFilter, String titleFilter, long userFilter) {
        return publicationRepository.getSize(topicFilter, titleFilter, userFilter);
    }

    @Override
    public int subscribeToPublication(long userId, long publicationId) throws AppException {

        BigDecimal amount = accountRepository.getAmountByUserId(userId);
        Publication publication = findByID(publicationId);

        if (publicationRepository.isSubscribeToPublication(userId, publicationId)) {
            return -1;
        }
        if (publication.getPrice().compareTo(amount) > 0) {
            return -2;
        }

        return publicationRepository.subscribeToPublication(userId, publicationId, publication.getPrice());
    }
}
