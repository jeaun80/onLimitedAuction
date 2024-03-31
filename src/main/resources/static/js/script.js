
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

    fetch('/images/read?fileName=/Users/gimjingwon/Documents/Auction/image/09a2c770-59fe-4fd0-8571-404836b2eaf0_IMG_9406 2 (1).jpg')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.blob(); // 이미지 URL을 JSON으로 받아옴
        })
        .then(data => {
            // 이미지 URL을 이용하여 이미지를 시각화하여 화면에 추가
            const imageUrl = data; // 첫 번째 이미지만 사용
            const img = document.createElement('img');
            img.src = imageUrl;
            document.getElementById('imageContainer').appendChild(img);
        })
        .catch(error => {
            console.error('There has been a problem with your fetch operation:', error);
        });
    // 페이지 로드 시 이미지를 가져오도록 설정
    window.onload = fetchImage;
    console.log("경매열기 버튼이 클릭되었습니다.");
    // 원하는 기능 추가
}

function login() {
    fetch("api/item/1")
        .then(response => {return response.json()})
        .then(data => {
            console.log(data); // 서버에서 받은 데이터

            document.getElementById('productImage').src = data.imagePath;
        });
    console.log("로그인 버튼이 클릭되었습니다.");
    // 원하는 기능 추가
    window.onload = fetchProductDetails;

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