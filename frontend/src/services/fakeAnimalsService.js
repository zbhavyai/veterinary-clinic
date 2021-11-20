const animals = [
    {
        "animalId": 1,
        "name": "Ace",
        "type": "Dog",
        "breed": "German shepherd",
        "birthDate": null,
        "sex": "1",
        "status": "INACTIVE",
        "theOwner": {
            "ownerId": 1,
            "firstName": "Damian",
            "middleName": "Bruce",
            "lastName": "Wayne",
            "role": null,
            "contactNumber": "4036653061",
            "emailId": "damian.bruce.wayne@ucalgary.ca",
            "address": "Wayne Manor"
        },
        "tattooNum": 130110477,
        "rfidNumber": "229664665",
        "microChipNumber": "229348845",
        "weight": null,
        "coatColor": "Black",
        "continuousMedication": null,
        "animalPhotoList": [],
        "animalTreatmentList": [],
        "animalIssueList": [],
        "age": 0
    },
    {
        "animalId": 2,
        "name": "Ampersand",
        "type": "Monkey",
        "breed": "Capuchin monkey",
        "birthDate": null,
        "sex": "0",
        "status": "YELLOW",
        "theOwner": {
            "ownerId": 2,
            "firstName": "Yorrick",
            "middleName": "",
            "lastName": "Brown",
            "role": null,
            "contactNumber": "4034097804",
            "emailId": "yorrick.brown@ucalgary.ca",
            "address": ""
        },
        "tattooNum": 204261377,
        "rfidNumber": "313829102",
        "microChipNumber": "233453231",
        "weight": null,
        "coatColor": "Brown",
        "continuousMedication": null,
        "animalPhotoList": [],
        "animalTreatmentList": [],
        "animalIssueList": [],
        "age": 0
    },
    {
        "animalId": 3,
        "name": "Bat Cow",
        "type": "Cow",
        "breed": null,
        "birthDate": null,
        "sex": "0",
        "status": "RED",
        "theOwner": {
            "ownerId": 1,
            "firstName": "Damian",
            "middleName": "Bruce",
            "lastName": "Wayne",
            "role": null,
            "contactNumber": "4036653061",
            "emailId": "damian.bruce.wayne@ucalgary.ca",
            "address": "Wayne Manor"
        },
        "tattooNum": 171659869,
        "rfidNumber": "223023284",
        "microChipNumber": "202432386",
        "weight": null,
        "coatColor": "Brown, White",
        "continuousMedication": null,
        "animalPhotoList": [],
        "animalTreatmentList": [],
        "animalIssueList": [],
        "age": 0
    },
    {
        "animalId": 4,
        "name": "Comet",
        "type": "Horse",
        "breed": "Canadian horse",
        "birthDate": null,
        "sex": "1",
        "status": "YELLOW",
        "theOwner": {
            "ownerId": 3,
            "firstName": "Clark",
            "middleName": "Joseph",
            "lastName": "Kent",
            "role": null,
            "contactNumber": "4032991533",
            "emailId": "clark.joseph.kent@ucalgary.ca",
            "address": "Smallville"
        },
        "tattooNum": 225931412,
        "rfidNumber": "184668886",
        "microChipNumber": "345541619",
        "weight": null,
        "coatColor": "White",
        "continuousMedication": null,
        "animalPhotoList": [],
        "animalTreatmentList": [],
        "animalIssueList": [],
        "age": 0
    },
    {
        "animalId": 5,
        "name": "Krypto",
        "type": "Dog",
        "breed": "Labrador retriever",
        "birthDate": null,
        "sex": "0",
        "status": "YELLOW",
        "theOwner": {
            "ownerId": 3,
            "firstName": "Clark",
            "middleName": "Joseph",
            "lastName": "Kent",
            "role": null,
            "contactNumber": "4032991533",
            "emailId": "clark.joseph.kent@ucalgary.ca",
            "address": "Smallville"
        },
        "tattooNum": 185030356,
        "rfidNumber": "170719459",
        "microChipNumber": "157412204",
        "weight": null,
        "coatColor": "White",
        "continuousMedication": null,
        "animalPhotoList": [],
        "animalTreatmentList": [],
        "animalIssueList": [],
        "age": 0
    },
    {
        "animalId": 6,
        "name": "Snowy",
        "type": "Dog",
        "breed": "Wire Fox Terrier",
        "birthDate": null,
        "sex": "1",
        "status": "GREEN",
        "theOwner": {
            "ownerId": 4,
            "firstName": "Tintin",
            "middleName": "",
            "lastName": "",
            "role": null,
            "contactNumber": "4031678053",
            "emailId": "tintin@ucalgary.ca",
            "address": "Marlinspike Hall, Belgium"
        },
        "tattooNum": 225864215,
        "rfidNumber": "188758885",
        "microChipNumber": "255400812",
        "weight": null,
        "coatColor": "White",
        "continuousMedication": null,
        "animalPhotoList": [],
        "animalTreatmentList": [],
        "animalIssueList": [],
        "age": 0
    },
    {
        "animalId": 7,
        "name": "Streaky",
        "type": "Cat",
        "breed": "Abyssinian",
        "birthDate": null,
        "sex": "1",
        "status": "YELLOW",
        "theOwner": {
            "ownerId": 3,
            "firstName": "Clark",
            "middleName": "Joseph",
            "lastName": "Kent",
            "role": null,
            "contactNumber": "4032991533",
            "emailId": "clark.joseph.kent@ucalgary.ca",
            "address": "Smallville"
        },
        "tattooNum": 209577225,
        "rfidNumber": "177988155",
        "microChipNumber": "267795413",
        "weight": null,
        "coatColor": "Orange",
        "continuousMedication": null,
        "animalPhotoList": [],
        "animalTreatmentList": [],
        "animalIssueList": [],
        "age": 0
    }
];

export function getAnimals() {
    return animals;
  }


