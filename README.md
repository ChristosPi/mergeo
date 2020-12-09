## merGeo: Integration Platform For Linked Data Management Tools

BSc thesis, available at: https://pergamos.lib.uoa.gr/uoa/dl/object/2075232

***

#### Α) Installation notes
Due to some backend services, merGeo can only be supported on Linux systems.
Follow the next steps and install the required applications/modules in order to
successfully deploy merGeo on Tomcat.

##### 1. Apache Tomcat (http://tomcat.apache.org/)
Apache Tomcat can be downloaded here: (https://tomcat.apache.org/download-90.cgi),
depends on the operating system and its distribution. You can find installation
instructions here (http://tomcat.apache.org/tomcat-9.0-doc/setup.html). After installation,
you can point your browser at this location (http://localhost:8080/) to verify that the
deployment succeeded. You can find information of how-to deploy an application here:
(http://tomcat.apache.org/tomcat-9.0-doc/deployer-howto.html).

**Important!** In order to create folders and edit files inside Tomcat location, we have to
change the permissions to Tomcat folder and to all its sub-folders. This can be done
through the graphical interface of our operating system, on “tomcat” folder:

```
Right-Click->Permissions and change everything to “Create and delete files”.
```
Otherwise, through command line you can run:

```
$> sudo chmod –R 777 /tomcat
```
(We know it’s not quite safe to change the permissions in 777, this is going to be fixed in
the next updates)
##### 2. Installing PostgreSQL and PostGIS

1 -- Install PostgreSQL 9.0 or higher. More information can be found at http://www.postgresql.org/download/
```
$> sudo apt-get install postgresql-9.5
```
2 -- Install PostGIS 2.2 or higher. More information can be found at http://postgis.refractions.net/download/
```
$> sudo apt-get install postgresql-9.5-postgis-2.2
```

3 -- Provide a password for default user (postgres)
```
$> sudo -u postgres psql -c "ALTER USER postgres WITH PASSWORD 'postgres';"
```

**Creating a spatially enabled database**

Spatially-enabled databases permit the use of spatial function calls. MonetDB creates spatially-enabled databases by default if you have enabled the geom module. More information on how to create a spatially-enabled database in PostGIS can be found at http://postgis.refractions.net/docs/.

1 -- Set postgis-2.2 path.
```
$> POSTGIS_SQL_PATH=`pg_config --sharedir`/contrib/postgis-2.2
```
2 -- Create the spatial database that will be used as a template.
```
$> createdb -E UTF8 -T template0 template_postgis -U postgres
```
3 -- Add PLPGSQL language support.
```
$> createlang -d  template_postgis plpgsql -U postgres
```

**Load the PostGIS SQL routines**
```
$> psql -d template_postgis -f $POSTGIS_SQL_PATH/postgis.sql                                                                
$> psql -d template_postgis -f $POSTGIS_SQL_PATH/spatial_ref_sys.sql
```

**Allow users to alter spatial tables**
```
$> psql -d template_postgis -c "GRANT ALL ON geometry_columns TO PUBLIC;"
$> psql -d template_postgis -c "GRANT ALL ON geography_columns TO PUBLIC;"
$> psql -d template_postgis -c "GRANT ALL ON spatial_ref_sys TO PUBLIC;"
```

**Perform garbage collection**
```
$> psql -d template_postgis -c "VACUUM FULL;"
$> psql -d template_postgis -c "VACUUM FREEZE;"
```

**Allows non-superusers the ability to create from this template**
```
$> psql -d postgres -c "UPDATE pg_database SET datistemplate='true' WHERE datname='template_postgis';"
$> psql -d postgres -c "UPDATE pg_database SET datallowconn='false' WHERE datname='template_postgis';"
```

**Create a spatially-enabled database named endpoint**
```
$> createdb endpoint -T template_postgis
```
##### <!> Notice: In some psql commands you may need to add: –U postgres <!> 

##### <!> Notice 2: In WebController.java, in line 35, you have to change the variable:
```
// TODO - Change this folder to one folder inside your Computer where the Strabon App will be already deployed
// so as to take its content and use it as the default deploying endpoint application
    
public static String endpointFolder = "/opt/tomcat/endpoint/strabon-endpoint-3.3.2-SNAPSHOT/."

```


#### B) Additional info

**Default Strabon Endpoint info:**
```
Strabon Endpoint version    : strabon-endpoint-3.3.2-SNAPSHOT.war
Hostname    ("localhost");
Port        ("5432");
Dbengine    ("postgis");
Dbname      ("endpoint");
Username    ("postgres");
Password    ("postgres");
Cp_username ("endpoint");
Cp_password ("3ndpo1nt");
```
