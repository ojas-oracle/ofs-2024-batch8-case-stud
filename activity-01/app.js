import * as fs from "fs";
import Employee from "./emp.js";
import reader from "readline-sync";

try {


    var id = reader.question("Your id : ")
    var name = reader.question("Your name : ")
    var sal = reader.question("Your salary : ")

    // reading a JSON file synchronously
    const employee = new Employee(id , name , sal)
    const data = fs.readFileSync("emp.json");
    var empData = data.toString()

    var arr = []
    if(empData){
        arr = JSON.parse(empData)["employees"]
    }
    arr.push(employee)
    console.log(JSON.stringify({"employees" : arr}))
    fs.writeFile('emp.json', JSON.stringify({"employees" : arr}), function(err){

    });
    console.log("Data Added")

  } catch (error) {
    // logging the error
    console.error(error);
  
    throw error;
  }