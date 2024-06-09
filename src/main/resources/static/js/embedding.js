function send_embedding(prompt) {
    //send request & get response

    fetch(url + `api/embedding?prompt=${prompt}`)
        .then(response => console.log(response.json()))
        .catch(error => {
            console.error('Error:', error);
        });
    //
    // fetch('http://localhost:8080/api/embedding?prompt=asdf')
    //     .then(response => {
    //         // 응답 처리
    //     })
    //     .catch(error => {
    //         // 오류 처리
    //     });
}