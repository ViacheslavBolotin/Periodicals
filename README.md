# PROJECT Periodicals

## Description

Implement the work of the Periodicals system.

In the system there is a list of publications that are grouped by topic. 
The reader can subscribe to one or more publications.<br/>
For the list of publications it is necessary to realize the possibility:<br/>
	- sort publications by title;<br/>
	- sort publications by price;<br/>
	- samples of publications on a specific topic;<br/>
	- search for publications by title.<br/>
The reader registers in the system and has a personal account, which displays information about the publications to which he is subscribed.<br/>
An unregistered user cannot subscribe.
The reader has a personal account that he can replenish.<br/>
Funds are withdrawn from the account when subscribing to the publication.<br/>
The system administrator has the rights:<br/>
	- adding, deleting and editing publications;<br/>
	- blocking / unblocking- the user.


https://github.com/ViacheslavBolotin/Periodicals

### Details about the database

*Connection string for database is in the properties file `db.properties` using the key `connection.url`. 

* The database creation script In `sql` directory at the root in it (`db-create.sql`).

<img src="C:\Users\bolotin.v\IdeaProjects\Periodicals\DB schema.png"/>