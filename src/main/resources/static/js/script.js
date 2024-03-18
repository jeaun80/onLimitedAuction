
function enterAuction() {
    console.log("경매입장 버튼이 클릭되었습니다.");

    fetch('/testrest')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.text();
        })
        .then(data => {
            console.log(data); // 서버에서 받은 데이터

            // 서버로부터 받은 데이터를 화면에 표시
            document.getElementById('auctionResponse').innerHTML = data;
        })
        .catch(error => {
            console.error('There has been a problem with your fetch operation:', error);
        });
    // 원하는 기능 추가
}

function openAuction() {
    console.log("경매열기 버튼이 클릭되었습니다.");
    // 원하는 기능 추가
}

function login() {
    console.log("로그인 버튼이 클릭되었습니다.");
    // 원하는 기능 추가
}

function register() {
    console.log("회원가입 버튼이 클릭되었습니다.");
    // 원하는 기능 추가
}

function addProduct() {
    console.log("판매상품 등록 버튼이 클릭되었습니다.");
    // 원하는 기능 추가
}

function myPage() {
    console.log("마이페이지 버튼이 클릭되었습니다.");
    // 원하는 기능 추가
}