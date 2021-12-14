import React from 'react';
import axios from "axios";
import ReactApexChart from 'react-apexcharts';


import NavBar from './navbar';
import {

    Link
} from "react-router-dom";
class AddWeight extends React.Component {
    state = {
        animal: {},
        weights: [],
        owner: {},
        imageUrl: process.env.PUBLIC_URL + '/toobad.png',
        apiphotos: [],
        photos:[],
        weight:""
    };

    styles = {
        width: 18 + 'rem'
    };

    async componentDidMount() {
        const id = this.props.match.params.id;

        var animalIdUrl = "http://localhost:8080/api/v1/animals/" + id
        const { data: animal } = await axios.get(animalIdUrl, { headers: { 'Access-Control-Allow-Origin': true, }, });
        this.setState({ animal });

        var animalWeightUrl = "http://localhost:8080/api/v1/animals/" + id + "/weights"
        const { data: weights } = await axios.get(animalWeightUrl, { headers: { 'Access-Control-Allow-Origin': true, }, });
        this.setState({ weights });

        var ownerId = this.state.animal["ownerId"];
        var ownerUrl = "http://localhost:8080/api/v1/owners/" + ownerId;
        const { data: owner } = await axios.get(ownerUrl, { headers: { 'Access-Control-Allow-Origin': true, }, });
        this.setState({ owner });

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





        //const promise = axios.get('https://jsonplaceholder.typicode.com/posts')
    }


    handleChange(event) {
        this.setState({weight: event.target.value})

      }

    handleComment =(e)=>{
        const id = this.props.match.params.id;
        const user = this.props.match.params.user;
        const uid = this.props.match.params.uid;

        if(isNaN(Number(this.state.weight))){
            alert("Please enter valid Number!")

        } else{
            const message = {
                "massInKg": Number(this.state.weight),

                "recordedBy": {
                    "userId": uid
                }
            }




            const link = "http://localhost:8080/api/v1/animals/" + id + "/weights";
            axios.post(link, message,{headers:{}}).then(res => {
                console.log(res);
                console.log(res.data);
              });
            //window.location.reload(false);

            setTimeout(() => {
                this.props.history.push("/"+ user+"/" + uid +'/animals/'+id);
             }, 500);

        }


    };

    render() {
        const user = this.props.match.params.user;
        const id = this.props.match.params.id;
        const uid = this.props.match.params.uid;
        let weight = [];
        let dates = [];
        // console.log(this.state.animal["weight"]);



        if (this.state.weights.length > 0) {
            for (var i = 0; i < this.state.weights.length; i++) {
                weight.push(this.state.weights[i]["massInKg"]);
                dates.push(this.state.weights[i]["recordDate"]);
            }
        }

        else {
            weight = [0, 0, 0, 0, 0, 0, 0, 0, 0];
            dates = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep'];
        }

        const series = [{
            name: "Weight",
            data: weight
        }];

        const options = {
            chart: {
                height: 350,
                type: 'line',
                zoom: {
                    enabled: false
                }
            },
            dataLabels: {
                enabled: false
            },
            stroke: {
                curve: 'straight'
            },

            grid: {
                row: {
                    colors: ['#f3f3f3', 'transparent'], // takes an array which will be repeated on columns
                    opacity: 0.5
                },
            },
            xaxis: { categories: dates }
        }

        if(user == "t" || user == "at" || user == "a"){
            return <React.Fragment>

            <NavBar user = {user} uid = {uid}/>

            <div className="container">
                <div className="row">
                    <div className="col-sm">
                        <img src={this.state.imageUrl} width="300" 
                                height="300" alt="" />

                    </div>
                    <div className="col-sm">
                        <div className="jumbotron jumbotron-fluid">
                            <div className="container">
                                <h5 className="display-4">Basic Details</h5>
                                <p className="lead">Animal ID: {this.state.animal["animalId"]}</p>
                                <p className="lead">Name: {this.state.animal["name"]}</p>
                                <p className="lead">Breed: {this.state.animal["breed"]}</p>
                                <p className="lead">Age: {this.state.animal["age"]}</p>
                                <p className="lead">Sex: {this.state.animal["sex"]}</p>
                                <p className="lead">Status: {this.state.animal["status"]}  <Link to={"/" + user + "/" + uid +"/animals/" + id + "/status"} className="btn btn-secondary">Update</Link></p>
                            </div>
                        </div>
                    </div>

                </div>

                <div className="container">
                        <h1 className="display-4">Add Weight</h1>
                        <div className="row">
                        <div className="col-sm">
                            < input type="text" id="inputFilter" className="form-control" placeholder="Add Weight here" aria-label="First Name" aria-describedby="basic-addon2" value={this.state.comment} onChange={(e) =>this.handleChange(e)}/>
                            <button onClick={(e) => this.handleComment(e)} className="btn btn-primary">Add Weight</button>

                        </div>
                        </div>
                    </div>

                    <div className="row">
                    <div className="jumbotron jumbotron-fluid">
                        <div className="container">

                            <div id="chart">
                                <ReactApexChart options={options} series={series} type="line" height={350} />
                            </div>


                        </div>
                    </div>

                </div>



            </div>



        </React.Fragment>;

        }
        else{
            return <React.Fragment>

            <NavBar user = {user} uid = {uid}/>

            <div className="container">
                <div className="row">
                    <div className="col-sm">
                        <img src={this.state.imageUrl} width="300" 
                                height="300" alt="" />

                    </div>
                    <div className="col-sm">
                        <div className="jumbotron jumbotron-fluid">
                            <div className="container">
                                <h5 className="display-4">Basic Details</h5>
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
                    <div className="col-sm">
                        <div className="jumbotron jumbotron-fluid">
                            <div className="container">
                                <h5 className="display-4">Owner Details</h5>
                                <p className="lead">Owner Name: {this.state.owner["fullName"]}</p>
                                <p className="lead">Owner Contact: {this.state.owner["contactNumber"]}</p>
                                <p className="lead">Owner Email: {this.state.owner["emailId"]}</p>
                                <p className="lead">Owner Address: {this.state.owner["address"]}</p>
                            </div>
                        </div>

                    </div>
                    <div className="col-sm">
                        <div className="jumbotron jumbotron-fluid">
                            <div className="container">
                                <h5 className="display-4">Identification Details</h5>
                                <p className="lead">RFID Number: {this.state.animal["rfidNumber"]}</p>
                                <p className="lead">Microchip: {this.state.animal["microChipNumber"]}</p>
                                <p className="lead">Tattoo: {this.state.animal["tattooNum"]}</p>
                                <p className="lead">Coat Color: {this.state.animal["coatColor"]}</p>
                            </div>
                        </div>
                    </div>

                </div>
                <div className="row">
                    <div className="jumbotron jumbotron-fluid">
                        <div className="container">
                            <h5 className="display-4">Weight Graph</h5>
                            <div id="chart">
                                <ReactApexChart options={options} series={series} type="line" height={350} />
                            </div>


                        </div>
                    </div>

                </div>
                <div className="row">
                    <div className="jumbotron jumbotron-fluid">
                        <div className="container">
                            <h5 className="display-4">Other Details</h5>
                            <p className="lead">Continuous Medication: {this.state.animal["continuousMedication"]}</p>


                        </div>
                    </div>

                </div>

                <div className="row">
                    <div className="jumbotron jumbotron-fluid">
                        <div className="container">
                            <h5 className="display-4">Photos</h5>
                            {this.state.photos.map(photo => (

                                <img key={photo} src={photo} width="200"
                                height="200" alt="" />

                            ))}


                        </div>
                    </div>

                </div>
                <div className="row">
                    <div className="btn-group" role="group" aria-label="Basic example">
                        <div><Link to={"/" + user + "/" + uid +"/animals/" + id + "/treatments"} className="btn btn-secondary">Treatment List</Link></div>
                        <div><Link to={"/" + user + "/" + uid +"/animals/" + id + "/issues"} className="btn btn-secondary">Issue List</Link></div>
                        <div><Link to={"/" + user + "/" + uid +"/animals/" + id + "/comments"} className="btn btn-secondary">Comment List</Link></div>
                    </div>

                </div>
            </div>



        </React.Fragment>;

        }



    }
}

export default AddWeight;