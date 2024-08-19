import "oj-c/input-text";
import "oj-c/input-password";
import "oj-c/input-number";
import "ojs/ojknockout";
import "ojs/ojinputtext";
import "ojs/ojlabel";
import "ojs/ojformlayout";
import { ojButton } from "ojs/ojbutton";
import "ojs/ojbutton";
import ko = require("knockout");
import { ojMessage } from "ojs/ojmessage";
import "ojs/ojmessages";
import MutableArrayDataProvider = require("ojs/ojmutablearraydataprovider");

class PayViewModel {
  readonly amount = ko.observable(0);
  readonly from = ko.observable(null);
  readonly to = ko.observable(null);

  messages: ojMessage.Message[];
  messagesDataprovider: MutableArrayDataProvider<null, ojMessage.Message>;

  constructor() {
    this.messages = [];
    this.messagesDataprovider = new MutableArrayDataProvider(this.messages);
  }

  public buttonAction = (event: ojButton.ojAction) => {
    let queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    if (urlParams.has("id") != true) {
      window.location.href = "http://localhost:8000/?ojr=login";
    }

    const raw = JSON.stringify({
      amount: this.amount(),
      from: { accountNumber: this.from() },
      to: { accountNumber: this.to() },
      completed_at: "2024-08-13T10:06:50Z",
      created_at: "2024-08-13T10:06:50Z",
      status: "completed",
    });

    fetch("http://localhost:8080/banking/pay/" + urlParams.get("id"), {
      headers: {
        "Content-Type": "application/json",
      },
      method: "POST",
      body: raw,
    })
      .then((response) => response.json())
      .then((result) => {
        console.log(result);
        console.log(raw);
        if (result["success"] != true) {
          this.messages.push({
            severity: "error",
            summary: "Failed to create transaction, Try again...",
            detail: result["message"],
          });
          this.messagesDataprovider.data = this.messages;
        } else {
            this.messages.push({
                severity: 'confirmation',
                summary: 'Transaction completed Successfully !',
                detail : 'You will be redirected to dashboard soon...'
              });
              this.messagesDataprovider.data = this.messages;
              setTimeout(() => {
                window.location.href = "http://localhost:8000/?ojr=dashboard&id=" + urlParams.get("id");
              }, 2000); 
          // let router = Router.rootInstance;
        //   window.location.href =
        //     "http://localhost:8000/?ojr=dashboard&id=" + result["result"];
          // console.log(router.currentState())
        }
      })
      .catch((error) => {
        this.messages.push({
          severity: "error",
          summary: "Login Failed...",
          detail: error["message"],
        });
        this.messagesDataprovider.data = this.messages;
      });

    return true;
  };
}

export = PayViewModel;
