package com.epam.bolotin.periodicals.model.builder;

import com.epam.bolotin.periodicals.model.db.entity.RecordReportPublication;
import com.epam.bolotin.periodicals.model.service.dto.RecordReportPublicationDto;

import java.util.ArrayList;
import java.util.List;

public class DtoBilder {

    public static List<RecordReportPublicationDto> getRecordReportPublicationDtoList(List<RecordReportPublication> recordReportPublication){

        List<RecordReportPublicationDto> ListOfRecordReportPublicationDto = new ArrayList<>();

        for (RecordReportPublication item: recordReportPublication) {

            ListOfRecordReportPublicationDto.add(fillRecordReportPublicationDto(item));
        }
        return ListOfRecordReportPublicationDto;
    }

    private static RecordReportPublicationDto fillRecordReportPublicationDto(RecordReportPublication item){

        RecordReportPublicationDto recordReportPublicationDto = new RecordReportPublicationDto();
        recordReportPublicationDto.setTitle(item.getTitle());
        recordReportPublicationDto.setTopicName(item.getTopicName());
        recordReportPublicationDto.setQuantity(item.getQuantity());
        recordReportPublicationDto.setAmount(item.getAmount());
        return recordReportPublicationDto;
    }
}
