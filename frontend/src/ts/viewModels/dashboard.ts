import * as ko from "knockout";
import { whenDocumentReady } from "ojs/ojbootstrap";
import "ojs/ojtable";
import "ojs/ojknockout";
import { RESTDataProvider } from 'ojs/ojrestdataprovider';

type Transaction = { id: number; from : number; to: number , type : string , amount : string , status : string };
type K = Transaction['id'];

class DashboardViewModel {

  readonly keyAttributes = 'id';
  readonly restDataProvider: RESTDataProvider<K, Transaction>;
  readonly custID = 22;

  constructor(){
  let queryString = window.location.search;
  const urlParams = new URLSearchParams(queryString);
  if(urlParams.has("id")!=true){
     window.location.href = "http://localhost:8000/?ojr=login";
  }
  this.restDataProvider = new RESTDataProvider({
    keyAttributes: this.keyAttributes,
    url: "http://localhost:8080/banking/dashboard/" + urlParams.get("id"),
    transforms: {
      fetchFirst: {
        request: async (options) => {
          const url = new URL(options.url);
          return new Request(url.href);
        },
        response: async ({ body }) => {
          try {
            var res:any = {}
            let data = [];
            
            for(let i=0; i<body['result'].length;i++){
              let type = body['result'][i].from.accountNumber!=this.custID ? "DEBIT" : "CREDIT"
              data.push({
                "id" : body['result'][i].id,
                "from" : body['result'][i].from.accountNumber,
                "to" : body['result'][i].to.accountNumber,
                "type" : type,
                "amount" : "₹ "  + body['result'][i].amount,
                "status" : body['result'][i].status
                })
            }
            res['data'] = data
            return res;
          } catch (error) {
            console.log(error)
            return null;
          }
        }
      }}})
    }
}

whenDocumentReady().then(function () {
  ko.applyBindings(new DashboardViewModel(), document.getElementById("table"));
});

export = DashboardViewModel;
