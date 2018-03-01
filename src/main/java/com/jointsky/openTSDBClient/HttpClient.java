package com.jointsky.openTSDBClient;

import java.io.IOException;

import com.jointsky.openTSDBClient.builder.MetricBuilder;
import com.jointsky.openTSDBClient.response.Response;
import com.jointsky.openTSDBClient.request.QueryBuilder;
import com.jointsky.openTSDBClient.response.SimpleHttpResponse;

public interface HttpClient extends Client {

	Response pushMetrics(MetricBuilder builder,
                         ExpectResponse exceptResponse) throws IOException;

	SimpleHttpResponse pushQueries(QueryBuilder builder,
                                   ExpectResponse exceptResponse) throws IOException;
}