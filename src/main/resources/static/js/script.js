
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
async function fetchLiveStreams(pageIndex,topId,sizePerPage) {
    try {
        const response = await fetch('/api/bid?pageIndex=${pageIndex}&topId=${topId}&sizePerPage=${sizePerPage}');
        const data = await response.json();
        console.log("라이브목록 가져오기 성공 "+data);
        return data;
    } catch (error) {
        console.error('Error fetching live streams:', error);
        return [];
    }
    console.log("회원가입 버튼이 클릭되었습니다.");
    // 원하는 기능 추가
}

// Function to populate live list dynamically
async function populateLiveList() {
    console.log("populateLiveList excuted");
    const liveListDiv = document.getElementById('liveList');
    const urlParams = new URLSearchParams(window.location.search);
    const pageIndex = urlParams.get('pageIndex') || 0;
    const topId = urlParams.get('topId') || 3;
    const sizePerPage = urlParams.get('sizePerPage') || 12;
    const liveStreams = await fetchLiveStreams(pageIndex,topId,sizePerPage);
    liveStreams.forEach((stream, index) => {
        const streamLink = document.createElement('a');
        streamLink.href = '#';
        streamLink.textContent = stream.title;
        streamLink.addEventListener('click', () => {
            playLiveStream(stream.streamKey);
        });
        liveListDiv.appendChild(streamLink);
        liveListDiv.appendChild(document.createElement('br'));
    });
}

// Function to play selected live stream
function playLiveStream(streamKey) {
    const streamUrl = `https://localhost:8080.com/${streamKey}.m3u8`; // Assuming your stream URLs follow this pattern
    const player = videojs('livePlayer');
    player.src({
        src: streamUrl,
        type: 'application/x-mpegURL'
    });
    player.play();
}

// Populate live list when the page is loaded
// window.onload = populateLiveList;
function addProduct() {
    console.log("판매상품 등록 버튼이 클릭되었습니다.");
    // 원하는 기능 추가
}

function myPage() {
    console.log("마이페이지 버튼이 클릭되었습니다.");
    // 원하는 기능 추가
}

