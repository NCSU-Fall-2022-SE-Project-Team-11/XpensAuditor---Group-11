package com.xa.xpensauditor;

import static com.xa.xpensauditor.AddTransactionActivity.printLog;

import android.os.AsyncTask;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.resource.Emailv31;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Utility task class for sending email notification when transaction limits are hit.
 */
public class LimitEmailSender extends AsyncTask<Void, Integer, Boolean> {
    private String email;
    private String amt;
    private String msg;

    public LimitEmailSender(String msg, String email) {
        this.email = email;
        this.msg= msg;
        
    }

    private Boolean sendLimitEmailUpdate(String message) {
        MailjetClient client;
        MailjetRequest request;
        MailjetResponse response;

        String api_key = "3fa6a84f570bba92fac4628a585f8d7b";
        String secret_key = "9ad95e56fcbf5481e921d4ee3ec1ce7f";

        ClientOptions clientOptions = ClientOptions.builder().apiKey(api_key).apiSecretKey(secret_key).build();

        printLog("created the client and stuff, trying to send an email now!");

        client = new MailjetClient(clientOptions);

        printLog("created the client with the required options");

        String from_email_id = "spsusarl@ncsu.edu";
        String from_email_name = "Xpense Auditor Group 11";

        String subject = "XpenseAuditorG11 -> Oops Expense Limit Crossed!";
        String textpart = "Shared exceeded expense limit notification message";
        String content = "<h3>Seems like you've crossed your expense limit !</h3>"+" "+ msg;
        String id = "xpense_auditor_g11_message";

        try {
            request = new MailjetRequest(Emailv31.resource)
                    .property(Emailv31.MESSAGES, new JSONArray()
                            .put(new JSONObject()
                                    .put(Emailv31.Message.FROM, new JSONObject()
                                            .put("Email", from_email_id)
                                            .put("Name", from_email_name))
                                    .put(Emailv31.Message.TO, new JSONArray()
                                            .put(new JSONObject()
                                                    .put("Email", email)
                                                    ))
                                    .put(Emailv31.Message.SUBJECT, subject)
                                    .put(Emailv31.Message.TEXTPART, textpart)
                                    .put(Emailv31.Message.HTMLPART, content)
                                    .put(Emailv31.Message.CUSTOMID, id)));
            // catching exceptions if email fails
        } catch ( JSONException e ) {
            printLog("json exception when creating the contents for the email , " + e.getMessage());
            return false;
        }

        printLog("created a request with all the contents involved, " + request.toString());

        try {
            response = client.post(request);
        }catch  (MailjetException e) {
            printLog("exception when sending the email, " + e.getMessage());
            return false;
        }
        printLog("email response status : " + response.getStatus());

       printLog("email response data : " + response.getData());

        return true;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        int i=0;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        return sendLimitEmailUpdate("");

    }


}

