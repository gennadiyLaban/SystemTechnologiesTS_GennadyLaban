package com.laban.systemtechnologies.utils;

import android.content.Context;

import com.laban.systemtechnologies.com.systemtechnologiests_gennadylaban.R;
import com.laban.systemtechnologies.errorrs.exceptions.Error;
import com.laban.systemtechnologies.errorrs.exceptions.NetworkConnectionException;
import com.laban.systemtechnologies.errorrs.exceptions.ResponseContentError;
import com.laban.systemtechnologies.errorrs.exceptions.ServerError;

public class ErrorMessageHelper {

    public static String getErrorMessage(Error error, Context context) {
        String message;
        try {
            throw error;
        } catch (NetworkConnectionException e) {
            message = context.getString(R.string.network_connection_error_message);
        } catch (ResponseContentError e) {
            message = context.getString(R.string.response_error_message);
        } catch (ServerError e) {
            message = context.getString(R.string.server_error_message);
        } catch (Error e) {
            message = context.getString(R.string.default_error_message);
        }
        return message;
    }


}
