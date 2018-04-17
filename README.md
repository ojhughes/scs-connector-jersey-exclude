This is an attempt to reproduce the issue in SCS Starters issue [https://github.com/pivotal-cf/spring-cloud-services-starters/issues/21]

Jersey client should be excluded from the build in SCS Starters `1.6.3.RELEASE`

These steps allow for a Boot app to be started without any errors

* Clear local Maven cache
* ./mvnw clean spring-boot:run -s .settings.xml
