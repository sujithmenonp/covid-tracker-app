package io.ursha.tech;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

@Service
class Scheduler {

    private HttpClient httpClient;

    @Autowired
    private CovidCSVService covidCSVService;

    private static final String DATA_SOURCE_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";


    public Scheduler(){
        this.httpClient =  HttpClient.newHttpClient();
    }

    private DateTimeFormatter formatter =
            DateTimeFormatter.ofLocalizedDateTime( FormatStyle.SHORT )
                    .withLocale( Locale.UK )
                    .withZone( ZoneId.systemDefault() );

    @Scheduled( fixedRate = 10000*60 )
    @PostConstruct
    public void getCovidData() throws IOException,InterruptedException {

        //String date = DateTimeFormatter.ofPattern("MM-dd-yyyy").withZone(ZoneId.systemDefault()).format(Instant.now().minus(1, ChronoUnit.DAYS));
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(DATA_SOURCE_URL)).build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        covidCSVService.process(httpResponse.body());
    }

}
