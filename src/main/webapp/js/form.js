document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('eventForm');

    if (!form) return;

    form.addEventListener('submit', function (e) {
        let errorMessage = '';

        // // Current date and time
        // const now = new Date();
        //
        // // Input date and time combined into a single Date object
        // const dateInput = document.getElementById('eventDate');
        // const timeInput = document.getElementById('eventTime');
        // const eventDateTime = new Date(`${dateInput.value}T${timeInput.value}`);
        //
        // // Inputs for minimum character count validation
        // const eventNameInput = document.getElementById('eventName');
        // const eventLocationInput = document.getElementById('eventLocation');
        // const eventDescriptionInput = document.getElementById('eventDescription');
        //
        // // Form validation for required fields
        // if (!form.checkValidity()) {
        //     errorMessage += 'Please fill out all fields before submitting.\n';
        // }
        //
        // // Minimum character count validation
        // if (eventNameInput.value.length < 3) {
        //     errorMessage += 'The event name must be at least 3 characters long.\n';
        // }
        // if (eventLocationInput.value.length < 3) {
        //     errorMessage += 'The event location must be at least 3 characters long.\n';
        // }
        // if (eventDescriptionInput.value.length < 10) {
        //     errorMessage += 'The event description must be at least 10 characters long.\n';
        // }
        //
        // // Form validation for future date/time check
        // if (eventDateTime <= now) {
        //     errorMessage += 'The event date and time must be in the future.\n';
        // }

        if (errorMessage) {
            alert(errorMessage);
            e.preventDefault();
        }
    });
});
