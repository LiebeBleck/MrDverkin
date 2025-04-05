const availabilityData = /*[[${availabilityList}]]*/ [];
const availabilityMap = {};
availabilityData.forEach(day => {
    availabilityMap[day.date] = day.frontDoorQuantity;
});
console.log('Availability Map:', availabilityMap);

const frontDoorQuantity = document.querySelector('#frontDoorQuantity');
const inDoorQuantity = document.querySelector('#inDoorQuantity');

let numbers = '1234567890';

frontDoorQuantity.addEventListener('input', () => {
    const inputArray = frontDoorQuantity.value.split('');
    const currentVal = frontDoorQuantity.value;
    console.log(inputArray);

    const onlyNumbers = inputArray.every(char => numbers.includes(char));
    if (!onlyNumbers || currentVal.startsWith('0') && currentVal.length > 1) {
        frontDoorQuantity.value = frontDoorQuantity.value.slice(0, -1);
        console.log(frontDoorQuantity.value)
    }
});

inDoorQuantity.addEventListener('input', () => {
    const inputArray = inDoorQuantity.value.split('');
    const currentVal = inDoorQuantity.value;
    const onlyNumbers = inputArray.every(char => numbers.includes(char));

    if (!onlyNumbers || currentVal.startsWith('0') && currentVal.length > 1) {
        inDoorQuantity.value = inDoorQuantity.value.slice(0, -1);
        console.log(inDoorQuantity.value)
    }
});


document.addEventListener("DOMContentLoaded", function () {
    const elementCalendar = document.getElementById('dateOrdered');

    if (elementCalendar) {
        const picker = new Pikaday({
            field: elementCalendar,
            format: "YYYY-MM-DD",
            firstDay: 1,
            minDate: new Date(2024, 0, 1),
            maxDate: new Date(2025, 11, 31),
            yearRange: [2023, 2030],
            i18n: {
                previousMonth: "Предыдущий",
                nextMonth: "Следующий",
                months: ["Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"],
                weekdays: ["Воскресенье", "Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота"],
                weekdaysShort: ["Вс", "Пн", "Вт", "Ср", "Чт", "Пт", "Сб"]
            },
            onSelect: function(date) {
                // Форматируем дату вручную
                const year = date.getFullYear();
                const month = String(date.getMonth() + 1).padStart(2, '0'); // +1, так как месяцы 0-11
                const day = String(date.getDate()).padStart(2, '0');
                elementCalendar.value = `${year}-${month}-${day}`;
            },
            onDraw: function() {
                const days = document.querySelectorAll('.pika-day');
                days.forEach(dayElement => {
                    const year = dayElement.getAttribute('data-pika-year');
                    const month = String(Number(dayElement.getAttribute('data-pika-month')) + 1).padStart(2, '0');
                    const day = String(dayElement.getAttribute('data-pika-day')).padStart(2, '0');
                    const dateStr = `${year}-${month}-${day}`;
                    if (availabilityMap[dateStr] !== undefined) {
                        const availableDoors = availabilityMap[dateStr];
                        dayElement.innerHTML += `<br><small>${availableDoors} дв.</small>`;
                    }
                });
            },
            disableDayFn: function(date) {
                const year = date.getFullYear();
                const month = String(date.getMonth() + 1).padStart(2, '0');
                const day = String(date.getDate()).padStart(2, '0');
                const dateStr = `${year}-${month}-${day}`;
                return availabilityMap[dateStr] === 0;
            }
        });
    } else {
        console.error("Элемент с id 'dateOrdered' не найден!");
    }
});