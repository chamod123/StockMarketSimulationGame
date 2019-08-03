function getAllStocks() {
    console.log("called  get allStocks")// Remove when API is integrated
    return  new Promise((resolve, reject) => { 
        fetch("http://localhost:8081/allStock")
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
    return  new Promise((resolve, reject) => { 
        fetch(`http://localhost:8081/portofolio/${user}`)
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

function buyStock(user, stock, quantity) {
    var request={
        "username":user,
        "stock": stock,
        "quantity": quantity
    }
    var url = `http://localhost:8081/buyStock`
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
    
    var url = `http://localhost:8081/sellStock`
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
                console.log(myJson)
                resolve(myJson)
            })
            .catch(error => {
                console.log('error', error);
                reject(error)
            });
    })
}

// GET - get stocks Data by sector
function getStocksBySector(name) {
    console.log("called getStocksBySector")
    return new Promise((resolve, reject) => {
        fetch(`http://localhost:8081/stockBySector/${name}`)
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
         fetch('http://localhost:8081/accounts')
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
        fetch('http://localhost:8081/"transactions')
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
        fetch('http://localhost:8081/players')
        // fetch('https://api.myjson.com/bins/1azzr5') //test myjson
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
    var url = `http://localhost:8081/addPlayer/${id}`
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
        // fetch(`http://localhost:8081/players/${id}`)
        fetch('https://api.myjson.com/bins/8ymrh') //test myjson
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
    var url = "http://localhost:8081/players"
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

function handleUpdateProfile(request) {
    var url = 'https://api.myjson.com/bins'
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
        fetch('https://api.myjson.com/bins/zg0it') //test myjson
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
        fetch(`http://localhost:8081/login/${username}/${password}`)
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
function handleUpdatePaymentInfo(request) {
    var url = 'https://api.myjson.com/bins'
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
function handleUpdateBankBalance(request) {
    var url = 'https://api.myjson.com/bins'
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
function  handleSignUP(request) {
    var url = 'https://api.myjson.com/bins'
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
function  handlePaymentInfo(request) {
    var url = 'https://api.myjson.com/bins'
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
        fetch(`http://localhost:8081/stock/${id}`)
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
        fetch('http://localhost:8081/transactions')
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
      //  fetch(`http://localhost:8081/bankBalance${id}`)
      fetch('https://api.myjson.com/bins/kx92t')
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
        fetch(`http://localhost:8081/nextTurn`)
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
        fetch(`http://localhost:8081/winner`)
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
    handleUpdateProfile,
    getPaymentInfo,
    getBankBalance,
    handleUpdatePaymentInfo,
    handleUpdateBankBalance,
    signIn,
    addPlayer,
    getStocksBySector,
    getAllStocks,
    buyStock,
    sellStock
 //   UpdateProfile
}