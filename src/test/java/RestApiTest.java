import com.jayway.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.get;

public class RestApiTest {
    @Test
    public void getRequestFindCapital() throws JSONException{
        Response resp = get("http://restcountries.eu/rest/v1/callingcode/7");
        JSONArray jsonResp = new JSONArray(resp.asString());

        String capital = jsonResp.getJSONObject(0).getString("capital");
        AssertJUnit.assertEquals(capital, "Moscow");
    }



}

