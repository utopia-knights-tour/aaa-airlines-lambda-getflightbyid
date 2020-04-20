import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

import proxy.ApiGatewayProxyResponse;
import proxy.ApiGatewayRequest;

public class GetFlightByIdTests {

	private static Context context = null;
	private static LambdaLogger logger = null;

	@BeforeAll
	public static void beforeAll() {
		context = Mockito.mock(Context.class);
		logger = Mockito.mock(LambdaLogger.class);
		Mockito.when(context.getLogger()).thenReturn(logger);
	}

	@Test
	public void getFlightById() {
		ApiGatewayRequest request = Mockito.mock(ApiGatewayRequest.class);
		Map<String, String> pathParameters = new HashMap<String, String>();
		pathParameters.put("flightId", "1");
		Mockito.when(request.getPathParameters()).thenReturn(pathParameters);
		ApiGatewayProxyResponse response = new GetFlightById().handleRequest(request, context);
		assertEquals(200, response.getStatusCode());
	}

	@Test
	public void getFlightById400() {
		ApiGatewayRequest request = Mockito.mock(ApiGatewayRequest.class);
		Map<String, String> pathParameters = new HashMap<String, String>();
		pathParameters.put("flightId", "a");
		Mockito.when(request.getPathParameters()).thenReturn(pathParameters);
		ApiGatewayProxyResponse response = new GetFlightById().handleRequest(request, context);
		assertEquals(400, response.getStatusCode());
	}

	@Test
	public void getFlightById404() {
		ApiGatewayRequest request = Mockito.mock(ApiGatewayRequest.class);
		Map<String, String> pathParameters = new HashMap<String, String>();
		pathParameters.put("flightId", "0");
		Mockito.when(request.getPathParameters()).thenReturn(pathParameters);
		ApiGatewayProxyResponse response = new GetFlightById().handleRequest(request, context);
		assertEquals(404, response.getStatusCode());
	}

}
