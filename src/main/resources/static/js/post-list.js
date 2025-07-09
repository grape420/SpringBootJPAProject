const postTableWrapper = document.getElementById("post-table-wrapper");
const paginationWrapper = document.getElementById("pagination-wrapper");

document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("search-form").addEventListener("submit", function (e) {
        e.preventDefault();
        loadPosts(0);
    });

    loadPosts();
});

function resetSearch() {
    document.getElementById("keyword").value = "";
    document.getElementById("searchType").value = "all"; // 기본값으로 리셋
    document.getElementById("startDate").value = "";
    document.getElementById("endDate").value = "";
    loadPosts(0); // 초기화 후 다시 첫 페이지 로딩
}

async function loadPosts(page = 0) {
    // 1. 검색 폼 데이터 읽어서 FormData 객체로 변환
    const form = document.getElementById("search-form");
    const formData = new FormData(form);

    // 2. page 파라미터로 강제 세팅
    formData.set("page", page);

    // 3. FormData -> URLSearchParams로 변환 (URL 쿼리스트링 생성)
    const params = new URLSearchParams();
    formData.forEach((value, key) => {
        params.append(key, value);
    });

    // 4. Ajax 요청(GET /posts/select?파라미터들)
    const res = await fetch(`/posts/select?${params}`);
    const html = await res.text();

    // 5. 받아온 HTML(게시글 목록+페이징)을 #post-table-wrapper에 삽입
    document.getElementById("post-table-wrapper").innerHTML = html;

    // 6. 페이징 UI만 따로 다시 render (혹시 기존 html에 덮어쓰는 경우를 대비)
    const tempDiv = document.createElement("div");
    tempDiv.innerHTML = html;
    const pagination = tempDiv.querySelector("#pagination-wrapper");
    if (pagination) {
        document.getElementById("pagination-wrapper").innerHTML = pagination.innerHTML;
    }
}

// 날짜 유효성 검사 함수
function validateDateRange() {
    const startInput = document.getElementById("startDate");
    const endInput = document.getElementById("endDate");

    const startDate = startInput.value;
    const endDate = endInput.value;

    // 문자열 비교로 안전하게 처리
    if (startDate && endDate && startDate > endDate) {
        startInput.value = endDate;
    }
}


// 날짜 입력 변경 시 유효성 검사 실행
document.getElementById("startDate").addEventListener("change", validateDateRange);
document.getElementById("endDate").addEventListener("change", validateDateRange);

