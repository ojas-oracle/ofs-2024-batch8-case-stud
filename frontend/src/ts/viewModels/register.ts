import "oj-c/input-text";
import "oj-c/input-password";
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
import 'ojs/ojdatetimepicker';
import 'ojs/ojvalidationgroup';
import { ojValidationGroup } from 'ojs/ojvalidationgroup';
import "ojs/ojformlayout";
import "oj-c/text-area";
import { RESTDataProvider } from 'ojs/ojrestdataprovider';

type Transaction = { id: number; from : number; to: number , type : string , amount : number , status : string };
type K = Transaction['id'];


class RegisterViewModel {
  readonly username = ko.observable("");
  readonly password = ko.observable("");
  readonly number = ko.observable("");
  readonly email = ko.observable("");
  readonly address = ko.observable("");
  readonly groupValid: ko.Observable<string> = ko.observable('');
  messages: ojMessage.Message[];
  messagesDataprovider: MutableArrayDataProvider<null, ojMessage.Message>;
  readonly keyAttributes = 'id';


  constructor(){
    this.messages = [
      ];
      this.messagesDataprovider = new MutableArrayDataProvider(this.messages);

     
  }

  private _checkValidationGroup() {
    const tracker = document.getElementById('tracker') as ojValidationGroup;
    if (tracker.valid === 'valid') {
      return true;
    } else {
      // show messages on all the components that are invalidHiddden, i.e., the
      // required fields that the user has yet to fill out.
      tracker.showMessages();
      tracker.focusOn('@firstInvalidShown');
      return false;
    }
  }

  public buttonAction = (event: ojButton.ojAction) => {
    const valid = this._checkValidationGroup();
    
    // this.messages = [];
    // this.messagesDataprovider = new MutableArrayDataProvider(this.messages)

    if(valid){
      const raw = JSON.stringify({
        "loginId": this.username(),
        "password": this.password(),
        "address": this.address(),
        "email": this.email(),
        "phone": this.number()
      });
  
      fetch("http://localhost:8080/banking/register", {
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
                  summary: 'Register Failed...',
                  detail: result['message']
                })
                this.messagesDataprovider.data = this.messages;
          }else{
            this.messages.push({
              severity: 'confirmation',
              summary: 'User Registered Successfully !',
            });
            this.messagesDataprovider.data = this.messages;
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
    }
    
    return true;
  };
}

export = RegisterViewModel;
