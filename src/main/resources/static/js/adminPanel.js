// Функция для сортировки таблицы
function sortTable(columnIndex) {
    const table = document.getElementById("ordersTable");
    const rows = Array.from(table.rows).slice(1);  // Пропускаем заголовок
    let sortedRows = rows.sort((a, b) => {
        let aValue = a.cells[columnIndex].innerText.trim();
        let bValue = b.cells[columnIndex].innerText.trim();

        // Если это числа, то сортируем как числа
        if (!isNaN(parseFloat(aValue)) && !isNaN(parseFloat(bValue))) {
            return parseFloat(aValue) - parseFloat(bValue);
        }

        // Иначе сортируем строки
        return aValue.localeCompare(bValue);
    });

    // Перезаполняем таблицу отсортированными строками
    table.tBodies[0].append(...sortedRows);
}

// Функция для поиска по продавцу
function searchSeller() {
    let input = document.getElementById("sellerSearch").value.toLowerCase();
    let rows = document.querySelectorAll("#ordersTable tbody tr");

    rows.forEach(row => {
        let seller = row.cells[4].innerText.toLowerCase();
        if (seller.includes(input)) {
            row.style.display = "";
        } else {
            row.style.display = "none";
        }
    });
}