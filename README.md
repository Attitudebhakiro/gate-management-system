# gate-management-system
Register user
http://localhost:2661/auth/signup

{
	"name": "Sinior Guard",
  "email": "gaurd@gmail.com",
  "password": "Password123",
  "role": "ADMIN"

}

login
{
  "email": "gaurd@gmail.com",
  "password": "Password123"

}

create gate only Admin can create
http://localhost:2661/admin/createGate

{
  "name": "Staff Gate"
 
}

Create visit 
only Guard can create
{
  "gate_id": 2,
  "activityType": "ENTRY",
  "visitor": {
    "full_name": "John Banda",
    "phone": "263771234567",
    "national_id": "12-345678A12",
    "company": "ABC Logistics"
  }
}
