function setAvgAge(){
    const ageTds = document.querySelectorAll('.ageTd');
    const resultTd = document.querySelector('#resultTd');

    let sum = 0;
    for(const ageTd of ageTds){
        sum += parseInt(ageTd.textContent);
    }

    const result = sum / 3; 

    resultTd.textContent = result;
}