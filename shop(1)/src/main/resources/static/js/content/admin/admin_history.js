//부트스트랩이 제공하는 모달 태그를 선택하는 방법
const buy_detail_modal = new bootstrap.Modal('#buy-detail-modal');
//document.queryslector를 쓰지 않은 이유 : html은 구분을 못 해서 단순히 div 태그를 선택한 게 됨.

//모달 열기
//buy_detail_modal.show();
//모달 닫기
//buy_detail_modal.hide();

// 행 클릭 시 구매 상세 내역 조회 및 모달창 띄우기
function showModal(buyCode){

    const thead = document.querySelector('.modal-body > table > thead');
    const tbody = thead.nextElementSibling;
    let detailTbody = document.querySelector('.detailTbody');

    console.log(detailTbody);

    fetch('/admin/selectBuyDetail', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // 데이터명 : 데이터값
           buyCode : buyCode
        })
    })
    .then((response) => {
        if(!response.ok){
            alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
            return ;
        }
    
        //return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
        return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        console.log(data);

        thead.innerHTML='';

        let str1 = '';

        str1 = `
            <tr>
                <td class="table-active">구매번호</td>
                <td>${data.buyCode}</td>
                <td class="table-active">구매자ID</td>
                <td>${data.memberId}</td>
            </tr>
        `

        tbody.innerHTML = '';
        
        let str2 = '';

        str2 = `
            <tr>
                <td class="table-active">구매금액</td>
                <td>${data.buyPrice}</td>
                <td class="table-active">구매일시</td>
                <td>${data.buyDate}</td>
            </tr>
        `

        detailTbody.innerHTML = '';

        let str3 = '';

        data.buyDetailList.forEach((buyDetail, i) => {

            console.log(buyDetail);
        
        str3 +=  `
            <tr>
                <td>${data.buyDetailList.length - i}</td>
                <td>
                    <img width="70px" src="/upload/${buyDetail.itemVO.imgList[0].attachedFileName}">
                    ${buyDetail.itemVO.itemName}
                </td>
                <td>${buyDetail.buyCnt}</td>
                <td>${buyDetail.totalPrice}</td>
            </tr>
        `

        });
           
        thead.insertAdjacentHTML('afterbegin', str1);
        tbody.insertAdjacentHTML('afterbegin', str2);
        detailTbody.insertAdjacentHTML('afterbegin', str3);
        
        buy_detail_modal.show();


    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}