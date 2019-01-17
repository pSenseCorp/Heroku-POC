# Heroku-POC

## Heroku Integration with Salesforce using Heroku connect


### What is Heroku?

+  Heroku is a cloud platform that lets companies build, deliver, monitor and scale apps
+  Heroku provides custom buildpacks allowing developers to program apps in different languages like Ruby, Node.js, Java, Python, Clojure, Scala, Go and PHP.
+  Heroku has several addons which can be used to extend the applications.

### Heroku-Salesforce Integration

Common methods available for Heroku-Salesforce Integration:

+ Heroku Connect
+ Salesforce Connect
+ Salesforce REST APIs
+ Callouts
+ Canvas


### Heroku Connect

+  Heroku Connect provides both data replication and data proxies for Salesforce. 
+  This POC is based on data replication.Data replication synchronizes data between Salesforce and a Heroku Postgres database.

### Heroku Essentials

+  **Heroku CLI** - This can be downloaded onto local machine to work with heroku from command line interface.
+  **Deploy** - Most Heroku deployments are performed by GIT. Heroku has a git interface or can be deployed using GitHub.
+  **ProcFile** - Heroku apps will have a procfile which is used to specify the commands that needs to be run during app deployment. Different types of process types can be added to this file like the web process, worker processes, any other tasks that needs to be run. 
+  **Configuration and Config Vars** - Environment specific app configuration can be set in the config vars for the heroku apps.

### Data Storage

   Heroku postgres data store is used in this POC to store the data synced from salesforce using heroku connect. Heroku postgres is a   database as service based on the PostgreSQL.  

### Common Commands when using Heroku CLI

+  **heroku login** - Command to login to heroku CLI
+  **heroku create `app name`** - Create a heroku app, app name is optional, if not given a default name is assigned
+  **heroku ps:scale `process type` = `Number of dynos`** - Command to scale the app. Process types can be web, worker, queue etc.,
+  **heroku open** - Open the app on the browser
+  **heroku logs --tail** - Tail the logs for the heroku app
+  **heroku config:set `key`=`value`** - Set the config var on heroku
+  **heroku config** - view all the config vars set
+  **heroku pg** - provides the info on the apps postgres DB
+  **heroku pg:psql** - opens the terminal connecting to the heroku remote postgres DB. This allows to run any sql queries on the DB.
+  **heroku pg:credentials:url** - Gives the DB connection information, connection URL, UserName, password, host, port etc.,



