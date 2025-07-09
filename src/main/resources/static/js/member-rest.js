const $ = sel => document.querySelector(sel);
const api = '/api/members';

const toast = new bootstrap.Toast(document.getElementById("toast"));
function showToast(msg) {
    $('#toast .toast-body').textContent = msg;
    toast.show();
}

// READ
async function loadMembers() {
    const res = await fetch(api);
    if (!res.ok) throw new Error("목록 조회 실패");
    const list = await res.json();
    renderList(list);
}

function renderList(members) {
    const listEl = $("#member-list");
    listEl.innerHTML = "";

    members.forEach(m => {
        const card = document.createElement("div");
        card.className = "card p-3 shadow-sm d-flex flex-row justify-content-between align-items-center";

        const content = document.createElement("div");
        content.innerHTML = `<strong>${m.name}</strong> <span class="text-muted">(${m.email})</span>`;
        content.style.cursor = "pointer";
        content.onclick = () => openEditModal(m);

        const delBtn = document.createElement("button");
        delBtn.className = "btn btn-outline-danger btn-sm";
        delBtn.innerHTML = `<i class="bi bi-trash"></i>`;
        delBtn.onclick = async (e) => {
            e.stopPropagation();
            if (confirm("정말 삭제하시겠습니까?")) {
                await deleteMember(m.id);
            }
        };

        card.appendChild(content);
        card.appendChild(delBtn);
        listEl.appendChild(card);
    });
}

// CREATE
$("#btn-save").addEventListener("click", async () => {
    const body = {
        name: $("#name").value,
        email: $("#email").value
    };

    const res = await fetch(api, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(body)
    });

    if (!res.ok) return alert("등록 실패");
    showToast("등록 완료");
    $("#name").value = "";
    $("#email").value = "";
    await loadMembers();
});

// UPDATE (modal)
let currentId = null;
function openEditModal(member) {
    currentId = member.id;
    $("#edit-name").value = member.name;
    $("#edit-email").value = member.email;
    const modal = new bootstrap.Modal(document.getElementById("editModal"));
    modal.show();
}

$("#btn-update").addEventListener("click", async () => {
    const body = {
        name: $("#edit-name").value,
        email: $("#edit-email").value
    };

    const res = await fetch(`${api}/${currentId}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(body)
    });

    if (!res.ok) return alert("수정 실패");
    showToast("수정 완료");
    bootstrap.Modal.getInstance(document.getElementById("editModal")).hide();
    await loadMembers();
});

// DELETE
async function deleteMember(id) {
    const res = await fetch(`${api}/${id}`, { method: "DELETE" });
    if (!res.ok) return alert("삭제 실패");
    showToast("삭제 완료");
    await loadMembers();
}

// 초기 로드
loadMembers().catch(console.error);
