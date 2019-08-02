function getStocks() {
    console.log("called  getStocks")// Remove when API is integrated
    return  new Promise((resolve, reject) => { 
        fetch('https://api.myjson.com/bins/vntih')
            .then(function (response) {
                return response.json();
            })
            .then(function (myJson) {
                resolve(myJson)
            });
    })
}

function getAllStocks() {
    console.log("called  get allStocks")// Remove when API is integrated
    return  new Promise((resolve, reject) => { 
        fetch("http://localhost:8081/allStock")
            .then(function (response) {
                return response.json();
            })
            .then(function (myJson) {
                resolve(myJson)
            });
    })
}

function getMyStocks() {
    console.log("called")
    return  new Promise((resolve, reject) => { 
        fetch('https://api.myjson.com/bins/103y91')
            .then(function (response) {
                return response.json();
            })
            .then(function (myJson) {
                resolve(myJson)
            });
    })
}

// GET - get stocks Data by sector
function getStocksBySector(name) {
    console.log("called getStocksBySector")
    return new Promise((resolve, reject) => {
        fetch(`http://localhost:8081/stockBySector/${name}`)
            .then(function (response) {
                if (!response.ok) throw new Error(response.statusText);
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
        // fetch('http://localhost:8081/"allPlayers')
        fetch('https://api.myjson.com/bins/1cwg6l')
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
        fetch('https://api.myjson.com/bins/1cwg6l')
        //fetch('https://api.myjson.com/bins/1h3uvl')
        // fetch('http://localhost:8081/"allPlayers')
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
                if (!response.ok) throw new Error(response.statusText);
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
                if (!response.ok) throw new Error(response.statusText);
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
//post -Sign IN
function  handlePostSignIn(request) {
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

// GET - get getPortofolio
function getPortofolio(id) {
    console.log("called")
    return  new Promise((resolve, reject) => { 
        fetch(`http://localhost:8081/portofolio${id}`)
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
    getStocks,
    getMyStocks,
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
    handlePostSignIn,
    addPlayer,
    getStocksBySector,
    getAllStocks
 //   UpdateProfile
}