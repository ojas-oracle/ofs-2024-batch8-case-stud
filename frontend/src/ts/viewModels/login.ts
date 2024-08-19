import "oj-c/input-text";
import "oj-c/input-password";
import "oj-c/input-number";
import "ojs/ojknockout";
import "ojs/ojinputtext";
import "ojs/ojlabel";
import "ojs/ojformlayout";
import { ojButton } from "ojs/ojbutton";
import "ojs/ojbutton";
import Router = require("ojs/ojrouter");
import ko = require("knockout");
import { ojMessage } from 'ojs/ojmessage';
import 'ojs/ojmessages';
import MutableArrayDataProvider = require("ojs/ojmutablearraydataprovider");

class LoginViewModel {
  readonly username = ko.observable("");
  readonly password = ko.observable("");

  messages: ojMessage.Message[];
  messagesDataprovider: MutableArrayDataProvider<null, ojMessage.Message>;

  constructor(){
    this.messages = [
      ];
      this.messagesDataprovider = new MutableArrayDataProvider(this.messages);
  }

  public buttonAction = (event: ojButton.ojAction) => {
    // this.messages = [];
    // this.messagesDataprovider = new MutableArrayDataProvider(this.messages)

    const raw = JSON.stringify({
      id: this.username(),
      password: this.password(),
    });

    fetch("http://localhost:8080/banking/login", {
      headers: {
        "Content-Type": "application/json",
      },
      method: "POST",
      body: raw,
    })
      .then((response) => response.json())
      .then((result) => {
        console.log(result)
        console.log(raw)
        if(result['success']!=true){
            this.messages.push({
                severity: 'error',
                summary: 'Login Failed...',
                detail: result['message']
              })
              this.messagesDataprovider.data = this.messages;
        }else{
            let router = Router.rootInstance;
        }
      })
      .catch((error) => {
        this.messages.push({
            severity: 'error',
            summary: 'Login Failed...',
            detail: error['message']
          })
          this.messagesDataprovider.data = this.messages;
      });
    
    return true;
  };
}

export = LoginViewModel;
