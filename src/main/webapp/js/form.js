document.addEventListener('DOMContentLoaded', (event) => {
    const form = document.getElementById('event-form');

    form.addEventListener('submit', function (e) {
        e.preventDefault();

        // Current date and time
        const now = new Date();

        // Input date and time combined into a single Date object
        const dateInput = document.getElementById('eventDate');
        const timeInput = document.getElementById('eventTime');
        const eventDateTime = new Date(`${dateInput.value}T${timeInput.value}`);

        // Inputs for minimum character count validation
        const eventNameInput = document.getElementById('eventName');
        const eventLocationInput = document.getElementById('eventLocation');
        const eventDescriptionInput = document.getElementById('eventDescription');

        // Form validation for required fields
        if (!form.checkValidity()) {
            alert('Please fill out all fields before submitting.');
            return false;
        }

        // Minimum character count validation
        if (eventNameInput.value.length < 3) {
            alert('The event name must be at least 3 characters long.');
            return false;
        }
        if (eventLocationInput.value.length < 3) {
            alert('The event location must be at least 3 characters long.');
            return false;
        }
        if (eventDescriptionInput.value.length < 10) {
            alert('The event description must be at least 10 characters long.');
            return false;
        }

        // Form validation for future date/time check
        if (eventDateTime <= now) {
            alert('The event date and time must be in the future.');
            return false;
        }

        form.action = 'submitForm';
        form.submit();

        return true;
    });
});