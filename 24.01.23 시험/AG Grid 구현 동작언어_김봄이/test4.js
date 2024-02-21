// function addRow(){
//     const addBtn = document.querySelector('#addBtn');
//     const stuList = document.querySelector('.stu-list');
//     const tbody = stuList.lastElementChild;

// addBtn.addEventListener('click', ()=>{

//     let str = '';
//     str += '<tr>'
//         str += '<td><input type="text" class="name"></td>'
//         str += '<td><input type="text" class="score"></td>'
//     str += '</tr>'
    



//     tbody.insertAdjacentHTML('beforeend', str);

    

//     });

// }

// function setData(){

//     const setBtn = document.querySelector('#setBtn');
//     const parent = document.querySelector('.result');
//     const sumResult = document.querySelector('#totalScore');
//     // const first = parent.children[1]('#firstName').value;
//     // const needer = parent.lastElementChild.lastElementChild('#names').value;


// setBtn.addEventListener('click', ()=>{

//         const sumResult = document.querySelector('.result > div > div > input');
        
//         const scores = document.querySelectorAll('.score');
//         const names = document.querySelectorAll('.name');

//         let sum = 0;
//         for(let score of scores){
//             sum += score;
//         }

//     sumResult.insertAdjacentHTML('afterbegin', sum);
        
//     })
// }



//자바스크립트의 객체(다수의 데이터를 저장하는 변수)
const name = '홍';
const stu = {
    'name' : '홍길동',
    'age' : 20
};

console.log(stu);
console.log(`name = ${stu.name}`);
console.log(`age = ${stu.age}`);

//자바스크립트 배열
const arr = [];
arr[0] = 20;
arr[1] = 30;
arr[2] = 'java';

console.log(arr);

const arr1 = [20, 30, 'java'];

//push : 현재 배열 데이터에서 가장 마지막 다음 위치에 알아서 데이터 삽입
arr1.push(50);
arr1.push('홍');
console.log(arr1);

//객체 배열
const arr2 = [
    {
        'name' : '홍',
        'age' : 20
    },
    {
        'name' : '김',
        'age' : 30
    },
    {
        'name' : '이',
        'age' : 40
    }
];
console.log(arr2);
console.log(arr2[1].name);



//선생님 시연

function addRow(){
    const tbody = document.querySelector('.stu-list > tbody');
  
  
  str = `
   <tr>
      <td><input type="text"></td>
      <td><input type="text"></td>
   </tr>
   `;
  
    tbody.insertAdjacentHTML('beforeend', str);
  
  }
  
  function setData(){
  
   const trs = document.querySelectorAll('.stu-list > tbody > tr');
  
    //1. 총점 구하기
       let sum = 0;
       for(const trTag of trs){
            sum = sum + parseInt(trTag.querySelectorAll('input')[1].value);
        //특정 태그 안의 요소를 찾을 때
        }
        document.querySelector('#totalScore').value = sum;

    //2-1. 1등 학생명 구하기 첫 번째 방식------------------------------------------------//
    //가장 높은 점수가 있는 tr
    // let highScoreTr = trs[0];
    // for(const trTag of trs){
    //     if(parseInt(highScoreTr.querySelectorAll('input')[1].value) < parseInt(trTag.querySelectorAll('input')[1].value)){
    //         highScoreTr = trTag;
    //     }
    // }

    // const highScoreName = highScoreTr.querySelectorAll('input')[0].value;

    // document.querySelector('#firstName').value = highScoreName; ---------------------//


    //2-2. 1등 학생명 구하기 두 번째 방식------------------------------------------------//
    const stuList = [];
    for(const trTag of trs){
        const student = {
            'name' : trTag.querySelectorAll('input')[0].value,
            'score' : parseInt(trTag.querySelectorAll('input')[1].value)
        };
        stuList.push(student);
    }
    //console.log(stuList);

    let maxStu = stuList[0];
    for(const stu of stuList){
        if(maxStu.score < stu.score){
            maxStu = stu;
        }
    }

    document.querySelector('#firstName').value = maxStu.name;
    
  
    //3. 보충 학습 대상자 구하기
    let names = '';
    for(const stu of stuList){
        if(stu.score < 60){
            names += stu.name + ' ';
        }
    }

    document.querySelector('#names').value = names;
  }