package com.drawers.banklib.view;

import android.content.Context;
import android.support.annotation.NonNull;
import com.drawers.banklib.JavaInterface;
import com.drawers.banklib.model.PasswordModel;
import com.drawers.banklib.otpdialog.EnterManualDialog;

import static com.drawers.banklib.R.style.DialogTheme;
import static com.drawers.banklib.presenter.JavascriptInjectionModels.getOtpSubmitJavascript;

public class PasswordScreenRouter implements BankRouter, EnterManualDialog.Listener {

  @NonNull private final EnterManualDialog enterManualDialog;
  @NonNull private final JavaInterface javaInterface;
  @NonNull private final PasswordModel passwordModel;

  public PasswordScreenRouter(
      @NonNull Context context,
      @NonNull JavaInterface javaInterface,
      @NonNull PasswordModel passwordModel) {
    this.enterManualDialog = new EnterManualDialog(context, DialogTheme, this);
    this.javaInterface = javaInterface;
    this.passwordModel = passwordModel;
  }

  @Override public void attachToView() {
    enterManualDialog.attach();
  }

  @Override public void detachFromView() {
    enterManualDialog.detach();
  }

  @Override public void submitOtp(String otp) {
    javaInterface.loadJavaScript(getOtpSubmitJavascript(passwordModel, otp));
  }
}
