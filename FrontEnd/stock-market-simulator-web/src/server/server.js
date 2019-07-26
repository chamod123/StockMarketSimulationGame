function getStocks() {
    console.log("called  getStocks")
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

export {
    getStocks,
    getMyStocks
}