const updateItemCode = document.querySelector('#updateItemCode').value;

if(updateItemCode != 0){
    getDetail(updateItemCode);
}

//상품 목록 테이블의 행 클릭 시 상품의 상세 정보 조회
function getDetail(itemCode){

    fetch('/admin/selectItemDetail', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // 데이터명 : 데이터값
           itemCode : itemCode
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

        const detail_div = document.querySelector('.detail-div');
        detail_div.innerHTML = '';
        
        let str = '';

        console.log(data.itemDetail.itemCode);

        str += `
        <form action="/admin/updateItemDetail" method="post">
            <div class="row">
                <div class="col">
                    <h3>상품 기본 정보</h3>
                </div>
            </div>
                <table class="table table-borderless">
                <input type="hidden" name="itemCode" value="${data.itemDetail.itemCode}">
                    <tr>
                        <td>
                            카테고리
                        </td>
                        <td colspan="2"> 
                            <select class="form-select" name="cateCode">`;
                        data.cateList.forEach(cate => {
                            if(cate.cateCode == data.itemDetail.cateCode){
                                str += `<option value="${cate.cateCode}" selected>${cate.cateName}</option>`
                            }
                            else{
                                str += `<option value="${cate.cateCode}">${cate.cateName}</option>`
                            }
                        
                        });
                            
                        str += `           
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            상품명
                        </td>
                        <td colspan="2">
                            <input type="text" class="form-control" name="itemName" value="${data.itemDetail.itemName}">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            상품수량
                        </td>
                        <td colspan="2">
                            <input type="text" class="form-control" name="itemStock" value="${data.itemDetail.itemStock}">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            상품상태
                        </td>
                        <td>`;
                            if(data.itemDetail.itemStatus == 1){
                                str += `<input checked type="radio" name="itemStatus" value="1">준비 중`;
                            }else{
                                str += `<input type="radio" name="itemStatus" value="1">준비 중`;
                            }
                            
                        str += `</td>
                        <td>`
                            if(data.itemDetail.itemStatus == 2){
                                str += `<input checked type="radio" name="itemStatus" value="2">판매 중`;
                            }else{
                                str += `<input type="radio" name="itemStatus" value="2">판매 중`;
                            }
                        str += `</td>
                        <td>`
                            if(data.itemDetail.itemStatus == 3){
                                str += `<input checked type="radio" name="itemStatus" value="3">매진`;
                            }else{
                                str += `<input type="radio" name="itemStatus" value="3">매진`;
                            }
                        str += `</td>                       
                    </tr>
                </table>
                <div class="row">
                    <div class="col">
                        <h3>상품 이미지 정보</h3>
                    </div>
                </div>
                <table class="table table-borderless">
                    <tr>
                        <td>
                            메인 이미지
                        </td>
                        <td >`;
                        for(const img of data.itemDetail.imgList){
                            if(img.isMain == 'Y'){
                                str += `<span onclick="showModal('${img.attachedFileName}')">${img.originFileName}</span>`;
                                    
                            }
                        }
                str += `</td>
                    </tr>
                    <tr>
                        <td>
                            상세 이미지
                        </td>
                        <td>`;
                        let cnt = 0;
                        data.itemDetail.imgList.forEach((img, idx)=>{
                            if(img.isMain == 'N'){
                                if(cnt == 0){
                                    str += `
                                        <div class="col-9">
                                            <span onclick="showModal('${img.attachedFileName}')">${img.originFileName}</span>
                                        </div>
                                    `;

                                    cnt++;
                                }
                                else{
                                    str += `
                                        <div class="col-9">
                                            <span onclick="showModal('${img.attachedFileName}')">${img.originFileName}</span>
                                        </div>
                                    `;
                                }

                            
                            }
                        });
                            
                str += `</td>
                    </tr>
                </table>
            <input type="submit" class="col btn btn-primary d-grid" value="변경">
        </form>
        `

        detail_div.insertAdjacentHTML('afterbegin', str);


    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });

}

//이미지 모달창 띄우기
function showModal(attachedFileName){

    const img_modal = new bootstrap.Modal('#img-modal');
    
    const img_tag = document.querySelector('#img-modal img');
    img_tag.src = `/upload/${attachedFileName}`;

    img_modal.show();
}