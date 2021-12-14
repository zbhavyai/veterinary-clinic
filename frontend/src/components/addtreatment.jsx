import React from 'react';
import axios from "axios";

import NavBar from './navbar';

class AddTreatment extends React.Component {
    state = {
        animal: {},
        treatmentDesc: "",
        drugName: "",
        drugDose: "",
        deliveryMethod: "",
        imageUrl: process.env.PUBLIC_URL + '/toobad.png',
        apiphotos: [],
        photos:[],
    };

    async componentDidMount() {
        const id = this.props.match.params.id;

        var animalIdUrl = "http://localhost:8080/api/v1/animals/" + id
        const { data: animal } = await axios.get(animalIdUrl, { headers: { 'Access-Control-Allow-Origin': true, }, });
        this.setState({ animal });

        var animalTreatmentUrl = "http://localhost:8080/api/v1/animals/" + id + "/treatments"
        const { data: treatments } = await axios.get(animalTreatmentUrl, { headers: { 'Access-Control-Allow-Origin': true, }, });
        this.setState({ treatments });

        var animalphotoUrl = "http://localhost:8080/api/v1/animals/" + id + "/photos"
        const { data: apiphotos } = await axios.get(animalphotoUrl, { headers: { 'Access-Control-Allow-Origin': true, }, });
        this.setState({ apiphotos });
        
        
        var photourl = null;
        var paray = [];
        for (var i = 0; i< this.state.apiphotos.length;i++){
            photourl = "http://localhost:8080/api/v1/animals/" + id+ "/photos/" + this.state.apiphotos[i]["photoId"];
           
            paray.push(photourl);

        }

        this.setState({ photos: paray});

        if(this.state.photos.length>0){
            this.setState({
                imageUrl: this.state.photos[0]
            })
        }
        

        console.log(this.state.photos.length)
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
            "treatmentDate": null,
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
        

        
        setTimeout(() => {
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
            
            <h2 className="display-4">Treatment Logs</h2>
            <div className="row">
                <div className="col-sm">
                    <img src={this.state.imageUrl} width="300" 
                                height="300" alt="" />

                </div>
                <div className="col-sm">
                    <div className="jumbotron jumbotron-fluid">
                        <div className="container">
                            <h1 className="display-4">Basic Details</h1>
                            <p className="lead">Animal ID: {this.state.animal["animalId"]}</p>
                                <p className="lead">Name: {this.state.animal["name"]}</p>
                                <p className="lead">Breed: {this.state.animal["breed"]}</p>
                                <p className="lead">Age: {this.state.animal["age"]}</p>
                                <p className="lead">Sex: {this.state.animal["sex"]}</p>
                                <p className="lead">Status: {this.state.animal["status"]}</p>
                        </div>
                    </div>
                </div>

            </div>

            <div className="row">
                <div className="jumbotron jumbotron-fluid">
                    <div className="container">
                        <h1 className="display-4">Add Treatment</h1>
                        <div className="row">
                        <div className="col-sm">
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