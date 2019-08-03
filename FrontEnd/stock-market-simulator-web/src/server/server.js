//require('dotenv').config();

function getAllStocks() {
    return  new Promise((resolve, reject) => { 
        fetch(`http://${process.env.Server_Ip}:${process.env.Server_Port}/allStock`)
            .then(function (response) {
                if (!response.ok) throw new Error(response.status);
                return response.json();
            })
            .then(function (myJson) {
                resolve(myJson)
            })
            .catch(error => {
                reject(error)
            });
    })
}

function getPortofolio(user) {
    console.log("called portofolio")
    console.log(user)
    return  new Promise((resolve, reject) => { 
        fetch(`http://${process.env.Server_Ip}:${process.env.Server_Port}/portofolio/${user}`)
            .then(function (response) {
                if (!response.ok) throw new Error(response.json());
                return response.json();
            })
            .then(function (myJson) {
                resolve(myJson)
            })
            .catch(error => {
                reject(error)
            });
    })
}

function buyStock(user, stock, quantity) {
    console.log("buyStock")
    var request={
        "username":user,
        "stock": stock,
        "quantity": quantity
    }
    console.log(request)
    var url = `http://${process.env.Server_Ip}:${process.env.Server_Port}/buyStock`
    return new Promise((resolve, reject) => {
        fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body:JSON.stringify(request)
        })
            .then(function (response) {
                if (!response.ok) throw new Error(response.status);
                return response.json();
            })
            .then(function (myJson) {
                resolve(myJson)
            })
            .catch(error => {
                reject(error)
            });
    })
}

function sellStock(user, stock, quantity) {
    var request={
        "username":user,
        "stock": stock,
        "quantity": quantity
    }
    
    var url = `http://${process.env.Server_Ip}:${process.env.Server_Port}/sellStock`
    return new Promise((resolve, reject) => {
        fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body:JSON.stringify(request)
        })
            .then(function (response) {
                if (!response.ok) throw new Error(response.status);
                return response.json();
            })
            .then(function (myJson) {
                resolve(myJson)
            })
            .catch(error => {
                reject(error)
            });
    })
}

// GET - get stocks Data by sector
function getStocksBySector(name) {
    console.log("called getStocksBySector")
    return new Promise((resolve, reject) => {
        fetch(`http://${process.env.Server_Ip}:${process.env.Server_Port}/stockBySector/${name}`)
            .then(function (response) {
                if (!response.ok) throw new Error(response.status);
                return response.json();
            })
            .then(function (myJson) {
                resolve(myJson)
            })
            .catch(error=>console.log(error))
    })
}

function getLeaderBoard() {
    console.log("called")
    return  new Promise((resolve, reject) => { 
         fetch(`http://${process.env.Server_Ip}:${process.env.Server_Port}/accounts `)
            .then(function (response) {
                return response.json();
            })
            .then(function (myJson) {
                resolve(myJson)
            });
    })
}

function getTransactioHistory() {
    console.log("called")
    return  new Promise((resolve, reject) => {
        fetch(` http://${process.env.Server_Ip}:${process.env.Server_Port}/transactions `)
            .then(function (response) {
                return response.json();
            })
            .then(function (myJson) {
                resolve(myJson)
            });
    })
}

function getPlayers() {
    console.log("called getPlayers")
    return new Promise((resolve, reject) => {
        fetch(`http://${process.env.Server_Ip}:${process.env.Server_Port}/players `)
        // fetch(`https://api.myjson.com/bins/1azzr5') //test myjson
            .then(function (response) {
                if (!response.ok) throw new Error(response.status);
                return response.json();
            })
            .then(function (myJson) {
                resolve(myJson)
            })
            .catch(error=>console.log(error))
    })
}

function addPlayer(id) {
    var url = `http://${process.env.Server_Ip}:${process.env.Server_Port}/addPlayer/${id}`
    return new Promise((resolve, reject) => {
        fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            }
        })
            .then(function (response) {
                if (!response.ok) throw new Error(response.status);
                return response.json();
            })
            .then(function (myJson) {
                console.log(myJson)
                resolve(myJson)
            })
            .catch(error => {
                console.log('error', error);
                reject(error)
            });
    })
}

function getPlayerByID(id) {
    return new Promise((resolve, reject) => {
         fetch(`http://${process.env.Server_Ip}:${process.env.Server_Port}/players/${id}`)
      //  fetch(`https://api.myjson.com/bins/8ymrh') //test myjson
            .then(function (response) {
                console.log(response)
                return response.json();
            })
            .then(function (myJson) {
                resolve(myJson)
            })
    })
}

function postPlayers(data) {
    var url = "http://${process.env.Server_Ip}:${process.env.Server_Port}/players"
    return new Promise((resolve, reject) => {
        fetch(url, {
            method: 'POST', 
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
        })
            .then(function (response) {
                return response.json();
            })
            .then(function (myJson) {
                resolve(myJson)
            })
    })
}

function UpdateProfile(request) {
    var url = 'http://${process.env.Server_Ip}:${process.env.Server_Port}/updateplayers'
    return new Promise((resolve, reject) => {
        fetch(url, {
            method: 'POST', 
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(request),
        })
            .then(function (response) {
                return response.json();
            })
            .then(function (myJson) {
                resolve(myJson)
            })
    })
}
// Get -payment infromation of a player
function getPaymentInfo(id) {
    return new Promise((resolve, reject) => {
     //   fetch(`https://api.myjson.com/bins/zg0it') //test myjson
         fetch(`http://${process.env.Server_Ip}:${process.env.Server_Port}/accounts/${id}`)
            .then(function (response) {
                console.log(response)
                return response.json();
            })
            .then(function (myJson) {
                resolve(myJson)
            })
    })
}
function  signIn(username, password) {
    console.log(username)
    console.log(password)
    return  new Promise((resolve, reject) => { 
        fetch(`http://${process.env.Server_Ip}:${process.env.Server_Port}/login/${username}/${password}`)
            .then(function (response) {
                if (!response.ok) throw new Error(response.status);
                return response.json();
            })
            .then(function (myJson) {
                resolve(myJson)
            })
            .catch(error => {
                reject(error)
            });
    })
}
//post -Update Payment Information
function UpdatePaymentInfo(request) {
    var url = 'http://${process.env.Server_Ip}:${process.env.Server_Port}/accountupdate'
    return new Promise((resolve, reject) => {
        fetch(url, {
            method: 'POST', 
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(request),
        })
            .then(function (response) {
                return response.json();
            })
            .then(function (myJson) {
                resolve(myJson)
            })
    })
}
//post -Update Bank Balance
function UpdateBankBalance(request) {
    var url = 'http://${process.env.Server_Ip}:${process.env.Server_Port}/deposit'
    return new Promise((resolve, reject) => {
        fetch(url, {
            method: 'POST', 
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(request),
        })
            .then(function (response) {
                return response.json();
            })
            .then(function (myJson) {
                resolve(myJson)
            })
    })
}
//post -SignUP
function  SignUP(request) {
    var url = 'http://${process.env.Server_Ip}:${process.env.Server_Port}/players'
    return new Promise((resolve, reject) => {
        fetch(url, {
            method: 'POST', 
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(request),
        })
            .then(function (response) {
                return response.json();
            })
            .then(function (myJson) {
                resolve(myJson)
            })
    })
}
//post -PaymentInfo
function  PaymentInfo(request) {
    var url = 'http://${process.env.Server_Ip}:${process.env.Server_Port}/accountupdate'
    return new Promise((resolve, reject) => {
        fetch(url, {
            method: 'POST', 
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(request),
        })
            .then(function (response) {
                return response.json();
            })
            .then(function (myJson) {
                resolve(myJson)
            })
    })
}
// GET - get a stock Data
function getStockById(id) {
    console.log("called getPlayers")
    return new Promise((resolve, reject) => {
        fetch(`http://${process.env.Server_Ip}:${process.env.Server_Port}/stock/${id}`)
            .then(function (response) {
                console.log(response)
                return response.json();
            })
            .then(function (myJson) {
                resolve(myJson)
            })
    })
}


// GET - get all transaction Data
{/*function getTransactionHistory() {
    console.log("called")
    return  new Promise((resolve, reject) => { 
        fetch(`http://${process.env.Server_Ip}:${process.env.Server_Port}/transactions')
            .then(function (response) {
                return response.json();
            })
            .then(function (myJson) {
                resolve(myJson)
            });
    })
}*/
}

// GET - get Bank Balance for a player
function getBankBalance(id) {
    console.log("called")
    return  new Promise((resolve, reject) => { 
        fetch(`http://${process.env.Server_Ip}:${process.env.Server_Port}/bankBalance${id}`)
   //   fetch(`https://api.myjson.com/bins/kx92t')
            .then(function (response) {
                return response.json();
            })
            .then(function (myJson) {
                resolve(myJson)
            });
    })
}

// GET - nextTurn
function nextTurn() {
    console.log("called nextTurn")
    return  new Promise((resolve, reject) => { 
        fetch(`http://${process.env.Server_Ip}:${process.env.Server_Port}/nextTurn`)
            .then(function (response) {
                return response.json();
            })
            .then(function (myJson) {
                resolve(myJson)
            });
    })
}

// GET - winner
function winner() {
    console.log("called winner")
    return  new Promise((resolve, reject) => { 
        fetch(`http://${process.env.Server_Ip}:${process.env.Server_Port}/winner`)
            .then(function (response) {
                return response.json();
            })
            .then(function (myJson) {
                resolve(myJson)
            });
    })
}
	

export {
    getPortofolio,
    getPlayers,
    getPlayerByID,
    getLeaderBoard,
	postPlayers,
    getTransactioHistory,
    UpdateProfile,
    getPaymentInfo,
    getBankBalance,
    UpdateBankBalance,
    signIn,
    addPlayer,
    getStocksBySector,
    getAllStocks,
    buyStock,
    sellStock,
    PaymentInfo,
    UpdatePaymentInfo,
    SignUP
 //   UpdateProfile
}