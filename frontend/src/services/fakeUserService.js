const users = [
    {
        userId: 1,
        joiningdate: "oct 1, 1111",
        activationdate: "oct 2, 1111",
        terminationdate: "N/A",
        firstName: "Greg",
        middleName: "Stark",
        lastName: "Tony",
        role: 0,
        emailId: "greg.boorman@ucalgary.ca",
        u_passwordhash: "passw0rd",
        u_passwordsalt: "what?",
        status: "1"
    },
    {
        userId: 2,
        joiningdate: "oct 1, 1121",
        activationdate: "oct 23, 1131",
        terminationdate: "N/A",
        firstName: "Poly",
        middleName: "Tech",
        lastName: "Tony",
        role: 0,
        emailId: "Poly.boorman@ucalgary.ca",
        u_passwordhash: "passw0rd",
        u_passwordsalt: "what?",
        status: "0"
    },
    {
        userId: 3,
        joiningdate: "nov 1, 1111",
        activationdate: "dec 2, 2111",
        terminationdate: "N/A",
        firstName: "This",
        middleName: "is",
        lastName: "test",
        role: 0,
        emailId: "this.test@ucalgary.ca",
        u_passwordhash: "passw0rd",
        u_passwordsalt: "what?",
        status: "1"
    }





];

export function getUsers() {
    return users;
  }