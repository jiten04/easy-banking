package com.drawers.banklib.utils;

import android.support.annotation.NonNull;
import com.drawers.banklib.model.ButtonModel;
import com.drawers.banklib.model.OtpBaseModel;
import com.drawers.banklib.model.OtpModel;

import static com.drawers.banklib.utils.BankLibHelper.JAVASCRIPT_FUNCTION_TEMPLATE;

public class JavascriptInjectionModels {
  public static String getOtpSubmitJavascript(@NonNull OtpBaseModel otpModel, @NonNull String otp) {
    return String.format(JAVASCRIPT_FUNCTION_TEMPLATE, "document.getElementById('"
        + otpModel.getOtpInputSelector()
        + "').value = '"
        + otp
        + "';\n"
        + "document.getElementById('"
        + otpModel.getButtons().get(ButtonModel.Type.SUBMIT).getSelector()
        + "').click();", "submit_" + otpModel.getName());
  }

  public static String getResendOtpJavascript(@NonNull OtpModel otpModel) {
    return String.format(JAVASCRIPT_FUNCTION_TEMPLATE,
        (otpModel.getButtons().containsKey(ButtonModel.Type.RESEND_OTP1)?
        "document.getElementById('" + otpModel.getButtons().get(ButtonModel.Type.SUBMIT).getSelector() + "').click();": "")
        +
            (otpModel.getButtons().containsKey(ButtonModel.Type.RESEND_OTP2)?
            "document.getElementById('" + otpModel.getButtons().get(ButtonModel.Type.SUBMIT).getSelector() + "').click();": ""),
        "submit_" + otpModel.getName());
  }

  public static String getOtpCancelJavascript(@NonNull OtpModel otpModel) {
    return String.format(JAVASCRIPT_FUNCTION_TEMPLATE,
        "document.getElementById('" + otpModel.getButtons()
            .get(ButtonModel.Type.CANCEL)
            .getSelector() + "').click();", "cancel_" + otpModel.getName());
  }

  //public static String getPaymentChoiceJavascript(@NonNull PaymentChoiceModel paymentChoiceModel) {
  //  return "document.getElementById('" + paymentChoiceModel.getRadioButtons(). + "').checked = true;";
  //}
}
