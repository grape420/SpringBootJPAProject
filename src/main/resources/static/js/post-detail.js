document.addEventListener("DOMContentLoaded", function () {
    const postId = document.getElementById("post-detail").dataset.postId;
    const commentList = document.getElementById("comment-list");
    const commentForm = document.getElementById("comment-form");

    // ✅ 댓글 목록 불러오기
    function loadComments() {
        fetch(`/comments/post/${postId}`)
            .then(res => res.json())
            .then(data => renderCommentList(data))
            .catch(err => console.error("댓글 목록 불러오기 실패:", err));
    }

    // ✅ 댓글 목록 렌더링
    function renderCommentList(comments) {
        commentList.innerHTML = "";

        if (comments.length === 0) {
            commentList.innerHTML = "<li class='list-group-item'>댓글이 없습니다.</li>";
            return;
        }

        comments.forEach(c => {
            const item = document.createElement("li");
            item.className = "list-group-item d-flex justify-content-between align-items-start";
            item.dataset.commentId = c.id;

            item.innerHTML = `
                <div>
                    <strong>${c.author}</strong> | ${c.createdAt?.replace("T", " ").substring(0, 16)}
                    <div>${c.content}</div>
                </div>
                <button class="btn btn-sm btn-danger btn-delete-comment">삭제</button>
            `;
            commentList.appendChild(item);
        });
    }

    // ✅ 댓글 등록
    commentForm.addEventListener("submit", function (e) {
        e.preventDefault();

        const formData = new FormData(this);
        const comment = {
            author: formData.get("author"),
            content: formData.get("content")
        };

        fetch(`/comments/post/${postId}`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(comment)
        })
            .then(res => {
                if (!res.ok) throw new Error("서버 응답 실패");
                return res.json();
            })
            .then(data => {
                console.log(data);
                if (!data) throw new Error("댓글 등록 실패");
                this.reset();
                loadComments();
            })
            .catch(err => {
                console.error("댓글 등록 실패:", err);
                alert("댓글 등록 중 오류가 발생했습니다.");
            });
    });

    // ✅ 댓글 삭제 (이벤트 위임)
    commentList.addEventListener("click", function (e) {
        if (!e.target.classList.contains("btn-delete-comment")) return;

        const li = e.target.closest("li");
        const commentId = li.dataset.commentId;

        if (!confirm("댓글을 삭제하시겠습니까?")) return;

        fetch(`/comments/${commentId}/delete`, {
            method: "POST",
            headers: { "Content-Type": "application/x-www-form-urlencoded" },
            body: new URLSearchParams({ postId }) // 필요 시 함께 전송
        })
            .then(res => {
                if (!res.ok) throw new Error("삭제 실패");
                loadComments();
            })
            .catch(err => {
                console.error("댓글 삭제 실패:", err);
                alert("댓글 삭제 중 오류가 발생했습니다.");
            });
    });

    // ✅ 초기 로딩
    loadComments();
});
