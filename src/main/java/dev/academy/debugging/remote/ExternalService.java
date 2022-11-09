package dev.academy.debugging.remote;

public class ExternalService {

    private static ExternalService externalService;
    private static long requestNo;

    private ExternalService(){}

    public static ExternalService getInstance(){
        if(externalService == null) {
            externalService = new ExternalService();
        }
        return externalService;
    }

    public String getValue(){
        if (requestNo == Long.MAX_VALUE) requestNo = 0;
        requestNo++;
        return "{" +
                    "format: string, " +
                    "content: lorem ipsum," +
                    "requestNo:" + requestNo +
                "}";
    }
}
