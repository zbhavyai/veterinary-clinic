import React, { Component } from 'react';
import axios from "axios";
import ReactApexChart from 'react-apexcharts';
import { useParams } from "react-router-dom";
import { withRouter } from "react-router";
import NavBar from './navbar';
import { Redirect } from 'react-router';
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link
} from "react-router-dom";
class AddTreatment extends React.Component {
    state = {
        animal: {},
        treatmentDesc: "",
        drugName: "",
        drugDose: "",
        deliveryMethod: "",
        imageUrl: 'https://picsum.photos/200',
    };

    async componentDidMount() {
        const id = this.props.match.params.id;

        var animalIdUrl = "http://localhost:8080/api/v1/animals/" + id
        const { data: animal } = await axios.get(animalIdUrl, { headers: { 'Access-Control-Allow-Origin': true, }, });
        this.setState({ animal });

        var animalTreatmentUrl = "http://localhost:8080/api/v1/animals/" + id + "/treatments"
        const { data: treatments } = await axios.get(animalTreatmentUrl, { headers: { 'Access-Control-Allow-Origin': true, }, });
        this.setState({ treatments });
    }

    handleDescChange(event) {
        this.setState({treatmentDesc: event.target.value})
        
      }

      handleDrugChange(event) {
        this.setState({drugName: event.target.value})
        
      }

      handleDosageChange(event) {
        this.setState({drugDose: event.target.value})
        
      }

      handledeliveryMethodChange(event) {
        this.setState({deliveryMethod: event.target.value})
        
      }

      handleTreatment =(e)=>{
        const id = this.props.match.params.id;
        const user = this.props.match.params.user;
        const uid = this.props.match.params.uid;
        let newDate = new Date()
        let day = newDate.getDate();
        let month = newDate.getMonth() + 1;
        let year = newDate.getFullYear();
        
        let dateString = year.toString() + "-" + month.toString()+ "-"+day.toString();
        console.log(dateString);

        const message = {
            "treatmentId": 5,
            "treatmentDesc": this.state.treatmentDesc,
            "treatmentDate": "2021-12-09",
            "treatedBy": {
                "userId": uid
            }
        }

        
       
        
        let link = "http://localhost:8080/api/v1/animals/" + id + "/treatments";
        axios.post(link, message,{headers:{}}).then(res => {
            console.log(res);
            console.log(res.data);
          });

          const status = {"status": "GREEN"};
        link = "http://localhost:8080/api/v1/animals/" + id;
        axios.put(link, status,{headers:{}});
        

        
        const timer = setTimeout(() => {
            this.props.history.push("/"+ user+"/" + uid +'/animals/'+id+"/treatments");
         }, 500);
        





    };



    // componentWillMount() {
    //     const id = this.props.match.params.id;
    //     // console.log(id);

    //     this.setState({
    //         animal: getAnimalbyId(id),
    //     });
    // }

    render() {
        const user = this.props.match.params.user;
        const uid = this.props.match.params.uid;


        return <React.Fragment>

            <NavBar user = {user} uid = {uid}/>
            
            <h2 class="display-4">Treatment Logs</h2>
            <div class="row">
                <div class="col-sm">
                    <img src={this.state.imageUrl} alt="" />

                </div>
                <div class="col-sm">
                    <div class="jumbotron jumbotron-fluid">
                        <div class="container">
                            <h1 class="display-4">Basic Details</h1>
                            <p class="lead">Animal ID: {this.state.animal["animalId"]}</p>
                                <p class="lead">Name: {this.state.animal["name"]}</p>
                                <p class="lead">Breed: {this.state.animal["breed"]}</p>
                                <p class="lead">Age: {this.state.animal["age"]}</p>
                                <p class="lead">Sex: {this.state.animal["sex"]}</p>
                                <p class="lead">Status: {this.state.animal["status"]}</p>
                        </div>
                    </div>
                </div>

            </div>

            <div class="row">
                <div class="jumbotron jumbotron-fluid">
                    <div class="container">
                        <h1 class="display-4">Add Treatment</h1>
                        <div class="row">
                        <div class="col-sm">
                            < input type="text" id="inputFilter" className="form-control" placeholder="Treatment Description" aria-label="First Name" aria-describedby="basic-addon2" value={this.state.treatmentDesc} onChange={(e) =>this.handleDescChange(e)}/>
                            
                            <button onClick={(e) => this.handleTreatment(e)} className="btn btn-primary">Add Treatment</button>
                        
                        </div>
                        </div>
                    </div>
                </div>

            </div>
        </React.Fragment>;
    }
}
 
export default AddTreatment;