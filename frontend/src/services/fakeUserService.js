const users = [
    {
        u_userid: 1,
        u_joiningdate: "oct 1, 1111",
        u_activationdate: "oct 2, 1111",
        u_terminationdate: "N/A",
        u_firstname: "Greg",
        u_middlename: "Stark",
        u_lastname: "Tony",
        u_role: 0,
        u_email: "greg.boorman@ucalgary.ca",
        u_passwordhash: "passw0rd",
        u_passwordsalt: "what?",
        u_status: "1"
    },
    {
        u_userid: 2,
        u_joiningdate: "oct 1, 1121",
        u_activationdate: "oct 23, 1131",
        u_terminationdate: "N/A",
        u_firstname: "Poly",
        u_middlename: "Tech",
        u_lastname: "Tony",
        u_role: 0,
        u_email: "Poly.boorman@ucalgary.ca",
        u_passwordhash: "passw0rd",
        u_passwordsalt: "what?",
        u_status: "0"
    },
    {
        u_userid: 3,
        u_joiningdate: "nov 1, 1111",
        u_activationdate: "dec 2, 2111",
        u_terminationdate: "N/A",
        u_firstname: "This",
        u_middlename: "is",
        u_lastname: "test",
        u_role: 0,
        u_email: "this.test@ucalgary.ca",
        u_passwordhash: "passw0rd",
        u_passwordsalt: "what?",
        u_status: "1"
    }





];

export function getUsers() {
    return users;
  }