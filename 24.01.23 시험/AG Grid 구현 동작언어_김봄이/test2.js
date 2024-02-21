const btn = document.querySelector('#btn');

btn.addEventListener('click', ()=>{

    const rowCnt = parseInt(document.querySelector('#rowInput').value);
    const colCnt = parseInt(document.querySelector('#colInput').value);
    const divTag = document.querySelector('.table-div');
    const spanTag = document.querySelector('span');

    divTag.removeChild(spanTag);

    let str = '';
    str += '<table>';
    for(let i = 0; i < rowCnt; i++){
        str += '<tr>';
        for(let j = 0; j < colCnt; j++){
            str += `<td>${i + 1}행 ${j + 1}열</td>`;
        }
        str += '</tr>';
    }
    str += '</table>';


    divTag.insertAdjacentHTML("afterbegin", str);


})

