document.addEventListener('DOMContentLoaded', () => {
    const attendForms = document.querySelectorAll('.attend-form');

    attendForms.forEach(form => {
        form.addEventListener('submit', function (e) {
            const attendee = prompt('Please enter your username');
            const creator = this.getAttribute('data-creator');

            if (attendee === creator) {
                alert('You cannot attend an event you created');
                e.preventDefault(); // Prevent form submission if user is the creator
                return;
            }

            if (attendee) {
                // Add or update the hidden input for attendee
                let attendeeInput = this.querySelector('input[name="attendee"]');
                if (!attendeeInput) {
                    attendeeInput = document.createElement('input');
                    attendeeInput.type = 'hidden';
                    attendeeInput.name = 'attendee';
                    this.appendChild(attendeeInput);
                }
                attendeeInput.value = attendee;
            } else {
                e.preventDefault(); // Prevent form submission if no username entered
            }
        });
    });
});
