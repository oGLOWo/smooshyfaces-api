# smooshyfaces-api for the people of the LA Cassandra Meetup
### This is a sample project for you to get familiar with how astyanax is used. I've left a lot of stuff unimplemented so you can try to figure it out and see if you can make the API work.

#### You need maven 3 and jdk 1.7 at least.
#### To build you can type `mvn clean compile`
#### To run it, you can type `mvn clean compile exec:java` - This will start the embedded jetty container and fire up the API that you can HTTP commands at http://localhost:8080/users/ or whatever paths you have
#### To quickly check if it's running, you can hit this url `http://localhost:8080/users/me/` It should give an unauthorized and tell you that your SMOOSHYID cookie is required

#### Here is the schema for Cassandra. Remember to use Cassandra 1.2.x otherwise I can't guarantee any of the stuff will work with the version of astyanax that I used

    create keyspace smooshyfaces
      with placement_strategy = 'NetworkTopologyStrategy'
      and strategy_options = {datacenter1 : 1}
      and durable_writes = true;

    use smooshyfaces;

    create column family pictures
      with column_type = 'Standard'
      and comparator = 'UTF8Type'
      and default_validation_class = 'BytesType'
      and key_validation_class = 'TimeUUIDType'
      and column_metadata = [
        {column_name : 'tags',
        validation_class : UTF8Type},
        {column_name : 'id',
        validation_class : UUIDType},
        {column_name : 'description',
        validation_class : UTF8Type},
        {column_name : 'picture_data_uri',
        validation_class : UTF8Type},
        {column_name : 'user_id',
        validation_class : UUIDType}];

    create column family picture_comments
      with column_type = 'Standard'
      and comparator = 'CompositeType(org.apache.cassandra.db.marshal.TimeUUIDType,org.apache.cassandra.db.marshal.TimeUUIDType)'
      and default_validation_class = 'UTF8Type'
      and key_validation_class = 'TimeUUIDType';

    create column family picture_likes
      with column_type = 'Standard'
      and comparator = 'TimeUUIDType'
      and default_validation_class = 'TimeUUIDType'
      and key_validation_class = 'TimeUUIDType';

    create column family picture_statistics
      with column_type = 'Standard'
      and comparator = 'TimeUUIDType'
      and default_validation_class = 'CounterColumnType'
      and key_validation_class = 'UTF8Type';

    create column family tagged_pictures
      with column_type = 'Standard'
      and comparator = 'TimeUUIDType'
      and default_validation_class = 'TimeUUIDType'
      and key_validation_class = 'UTF8Type';

    create column family timelines
      with column_type = 'Standard'
      and comparator = 'TimeUUIDType'
      and default_validation_class = 'TimeUUIDType'
      and key_validation_class = 'UTF8Type';

    create column family users
      with column_type = 'Standard'
      and comparator = 'UTF8Type'
      and default_validation_class = 'BytesType'
      and key_validation_class = 'TimeUUIDType'
      and column_metadata = [
        {column_name : 'id',
        validation_class : TimeUUIDType},
        {column_name : 'user_name',
        validation_class : UTF8Type},
        {column_name : 'email_address',
        validation_class : UTF8Type}];

    create column family user_emails
      with column_type = 'Standard'
      and comparator = 'UTF8Type'
      and default_validation_class = 'TimeUUIDType'
      and key_validation_class = 'UTF8Type';

    create column family user_lists
      with column_type = 'Standard'
      and comparator = 'TimeUUIDType'
      and default_validation_class = 'BytesType'
      and key_validation_class = 'UTF8Type';

    create column family user_names
      with column_type = 'Standard'
      and comparator = 'UTF8Type'
      and default_validation_class = 'TimeUUIDType'
      and key_validation_class = 'UTF8Type';

    create column family user_sessions
      with column_type = 'Standard'
      and comparator = 'UTF8Type'
      and default_validation_class = 'BytesType'
      and key_validation_class = 'TimeUUIDType'
      and column_metadata = [
        {column_name : 'token',
        validation_class : UTF8Type},
        {column_name : 'expiration_date',
        validation_class : DateType}];

    create column family user_sessions_inverted
      with column_type = 'Standard'
      and comparator = 'UTF8Type'
      and default_validation_class = 'BytesType'
      and key_validation_class = 'UTF8Type'
      and column_metadata = [
        {column_name : 'user_id',
        validation_class : TimeUUIDType},
        {column_name : 'expiration_date',
        validation_class : DateType}];

