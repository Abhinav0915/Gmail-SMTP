import { useState } from 'react';

function AttachmentMail() {
  const [recipient, setRecipient] = useState('');
  const [subject, setSubject] = useState('');
  const [msgBody, setMsgBody] = useState('');
  const [attachment, setAttachment] = useState<File | null>(null); // Adjust the type of attachment
  const [isSending, setIsSending] = useState(false);

  const handleAttachmentSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    setIsSending(true); // Set isSending to true when email sending starts

    const formData = new FormData();
    formData.append('recipient', recipient);
    formData.append('subject', subject);
    formData.append('msgBody', msgBody);
    if (attachment) { // Check if attachment is not null
      formData.append('attachment', attachment);
    }

    try {
      const response = await fetch('http://localhost:8080/sendAttachmentMail', {
        method: 'POST',
        body: formData
      });

      if (response.ok) {
        window.alert('Email sent successfully');
        // Optionally, you can display a success message or perform other actions here
      } else {
        window.alert('Failed to send email');
        // Optionally, you can display an error message or perform other actions here
      }
    } catch (error) {
      console.error('Error sending email:', error);
      // Optionally, you can display an error message or perform other actions here
    } finally {
      setIsSending(false); // Set isSending back to false after email sending is complete
    }
  };

  return (
    <div className="mt-5 w-full max-w-md p-4 bg-white border border-gray-200 rounded-lg shadow sm:p-6 md:p-8 dark:bg-gray-800 dark:border-gray-700">
      <form className="space-y-6" onSubmit={handleAttachmentSubmit}>
        <h5 className="text-xl font-medium text-gray-900 dark:text-white shadow-sm">Send Email With An Attachment</h5>
        <div>
          <label htmlFor="recipient" className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Add Recipient</label>
          <input
            type="text"
            name="recipient"
            id="recipient"
            value={recipient}
            onChange={(e) => setRecipient(e.target.value)}
            className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white"
            placeholder="johndoe@gmail.com"
            required
          />
        </div>
        <div>
          <label htmlFor="subject" className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Add Subject</label>
          <input
            type="text"
            name="subject"
            id="subject"
            value={subject}
            onChange={(e) => setSubject(e.target.value)}
            className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white"
            placeholder="Add Subject"
            required
          />
        </div>
        <div>
          <label htmlFor="attachment" className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Add Attachment</label>
          <input
            type="file"
            name="attachment"
            id="attachment"
            onChange={(e) => {
              if (e.target.files) {
                setAttachment(e.target.files[0]);
              }
            }}
            className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white"
            required
          />
        </div>
        <div>
          <label htmlFor="msgBody" className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Add Email Message</label>
          <textarea
            name="msgBody"
            id="msgBody"
            value={msgBody}
            onChange={(e) => setMsgBody(e.target.value)}
            className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white"
            placeholder="Add Message"
            required
          />
        </div>
        <button
          type="submit"
          className="w-full text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
          disabled={isSending} // Disable the button when isSending is true
        >
          {isSending ? 'Sending...' : 'Send Email'} {/* Change button text based on isSending state */}
        </button>
      </form>
    </div>
  );
}

export default AttachmentMail;
