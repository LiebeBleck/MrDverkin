// Передаем данные о доступности из Thymeleaf
const availabilityData = /*[[${availabilityList}]]*/ [];
const availabilityMap = {};
const availabilityMapinDoorQuantity = {};

availabilityData.forEach(day => {
    availabilityMap[day.date] = day.frontDoorQuantity;
    availabilityMapinDoorQuantity[day.date] = day.inDoorQuantity;
});
console.log('Availability Map:', availabilityMap);

async function confirmOrder(orderId) {
    let row = document.getElementById(`order-row-${orderId}`);
    if (!row) {
        console.error(`Не удалось найти строку с ID order-row-${orderId}`);
        alert('Ошибка: заказ с указанным ID не найден.');
        return;
    }

    const installerSelect = row.querySelector(`#installer-${orderId}`);
    if (!installerSelect) {
        console.error(`Не удалось найти элемент с ID installer-${orderId}`);
        alert('Ошибка: установщик не найден.');
        return;
    }
    const mainInstallerComment = row.querySelector(`#mainInstallerComment`).value;
    function commentByInstaller() {
        if (mainInstallerComment !== "") {
            console.log(mainInstallerComment)
            return mainInstallerComment;
        }else {
            return "Нет";
        }
    }

    const selectedInstaller = installerSelect.value;
    if (!selectedInstaller) {
        console.error('Установщик не выбран');
        alert('Пожалуйста, выберите установщика.');
        return;
    }

    const idElement = row.querySelector('#id');
    if (!idElement) {
        console.error('Не удалось найти элемент с ID id');
        alert('Ошибка: ID заказа не найден.');
        return;
    }
    const orderIdFromTable = idElement.textContent;

    const orderData = {
        orderId: orderIdFromTable,
        installerFullName: selectedInstaller,
        installerComment: commentByInstaller()
    };

    console.log('Отправляемые данные:', orderData);

    await fetch('/home/mainInstaller', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(orderData)
    })
        .then(response => {
            console.log('Статус ответа:', response.status);
            if (!response.ok && response.status !== 302) {
                return response.text().then(text => {
                    console.error('Тело ответа:', text);
                    throw new Error(`Ошибка сервера: ${response.status}`);
                });
            }
            window.location.reload();
        })
        .catch(error => {
            console.error('Ошибка:', error);
            alert('Произошла ошибка при подтверждении заказа');
        });
}


//123
document.addEventListener("DOMContentLoaded", function() {
    let hoverPopup = document.querySelector('#calendar-popup');
    let picker = new Pikaday({
        field: document.getElementById('calendar'),
        bound: false,
        position: { top: 0, left: 0 },
        onDraw: function() {
            const days = document.querySelectorAll('.pika-day');
            days.forEach(dayElement => {
                const year = dayElement.getAttribute('data-pika-year');
                const month = String(Number(dayElement.getAttribute('data-pika-month')) + 1).padStart(2, '0');
                const day = String(dayElement.getAttribute('data-pika-day')).padStart(2, '0');
                const dateStr = `${year}-${month}-${day}`;

                dayElement.addEventListener('mouseover', function(e) {
                    hoverPopup.style.display = 'block';
                    if (availabilityMap[dateStr] !== undefined) {
                        const availableDoors = availabilityMap[dateStr];
                        const availableInDoors = availabilityMapinDoorQuantity[dateStr];
                        hoverPopup.innerText = `Дата: ${dateStr}\n Входные двери ${availableDoors}\n Межкомнатные двери: ${availableInDoors}`;
                    }
                    // Позиционируем попап рядом с ячейкой
                    const rect = dayElement.getBoundingClientRect();
                    hoverPopup.style.top = `${rect.bottom + window.scrollY + 5}px`; // Чуть ниже ячейки
                    hoverPopup.style.left = `${rect.left + window.scrollX}px`; // Выравниваем по левой стороне

                });
                dayElement.addEventListener('mouseout', function() {
                    hoverPopup.style.display = 'none'; // Скрываем попап
                });
            });
        },
        disableDayFn: function(date) {
            const year = date.getFullYear();
            const month = String(date.getMonth() + 1).padStart(2, '0'); // getMonth() возвращает 0-11
            const day = String(date.getDate()).padStart(2, '0');
            const dateStr = `${year}-${month}-${day}`;
            const data = availabilityMap[dateStr];

            // Получаем текущую дату динамически
            const today = new Date();
            today.setHours(0, 0, 0, 0); // Устанавливаем начало текущего дня
            const checkDate = new Date(date);
            checkDate.setHours(0, 0, 0, 0); // Устанавливаем начало проверяемого дня

            // Отключаем прошедшие дни ИЛИ дни с нулевой доступностью
            return checkDate < today || (data && data.frontDoorQuantity === 0 && data.inDoorQuantity === 0);
        }
    });

    // Автоматическое обновление календаря каждые 24 часа
    setInterval(() => {
        picker.render(); // Перерисовываем календарь
        console.log('Календарь обновлен:', new Date());
    }, 24 * 60 * 60 * 1000); // 24 часа в миллисекундах
});

function DisabledOff(selectElement) {
    const row = selectElement.closest('tr');
    const disabledBtn = row.querySelector('.btn-confirm');
    if (selectElement.value !== "") {
        disabledBtn.disabled = false;
    } else {
        disabledBtn.disabled = true;
    }
}