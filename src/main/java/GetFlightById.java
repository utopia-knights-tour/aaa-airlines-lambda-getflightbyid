
import java.sql.SQLException;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;

import entity.Flight;
import proxy.ApiGatewayProxyResponse;
import proxy.ApiGatewayRequest;
import service.AgentService;

public class GetFlightById implements RequestHandler<ApiGatewayRequest, ApiGatewayProxyResponse> {

	private AgentService agentService = new AgentService();

	public ApiGatewayProxyResponse handleRequest(ApiGatewayRequest request, Context context) {
		LambdaLogger logger = context.getLogger();
		try {
			if (request.getPathParameters() == null || request.getPathParameters().get("flightId") == null) {
				return new ApiGatewayProxyResponse(400, null, null);
			}
			Flight flight = agentService.getFlightById(Long.parseLong(request.getPathParameters().get("flightId")));
			if (flight == null) {
				return new ApiGatewayProxyResponse(404, null, null);
			}
			return new ApiGatewayProxyResponse(200, null, new Gson().toJson(flight));
		} catch (NumberFormatException | SQLException e) {
			logger.log(e.getMessage());
			return new ApiGatewayProxyResponse(400, null, null);
		} catch (ClassNotFoundException e) {
			logger.log(e.getMessage());
			return new ApiGatewayProxyResponse(500, null, null);
		}
	}
}