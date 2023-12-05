import React, { useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import "./MyGigs.scss";
import { hosts } from "../../const";

function MyGigs() {
  const navigate = useNavigate();
  const [offers, setOffers] = React.useState([]);
  const [currentUser, setUser] = React.useState(null);
  useEffect(() => {
    fetch(`${hosts.backend}/api/myprofile`, {
      method: "GET",
      headers: {
        "Content-Type": "Application/json ; charset=UTF-8",
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    })
      .then((res) => {
        return res.json();
      })
      .then((data) => {
        if (data.error) {
          throw new Error(data.error);
        }
        setUser(data);
      })
      .catch((err) => {
        navigate("/login");
        setUser(null);
      });
  }, []);

  const offersFetching = () => {
    fetch(`${hosts.backend}/api/myoffers`, {
      method: "GET",
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`,
      },
    })
      .then((res) => {
        return res.json();
      })
      .then((data) => {
        setOffers(data);
      })
      .catch((err) => {
        console.log(err);
        toast.error("Couldnt fetch offers- check again later", {
          position: "bottom-right",
          autoClose: 10000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: true,
          progress: undefined,
          theme: "dark",
        });
      });
  };
  useEffect(() => {
    offersFetching();
  }, []);

  const handleDelete = (event, offerId) => {
    event.preventDefault();
    fetch(`${hosts.backend}/api/offers/${offerId}`, {
      method: "DELETE",
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`,
      },
    })
      .then((res) => {
        console.log(res);
        return res.json();
      })
      .then((data) => {
        console.log("data in deleting", data);
        toast.success("🦄 Offer deleted!", {
          position: "bottom-right",
          autoClose: 10000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: true,
          progress: undefined,
          theme: "dark",
        });
      })
      .then(() => {
        offersFetching();
      });
  };
  const handelUpdate = (event, id) => {
    event.preventDefault();
    const link = "/edit/" + id;
    navigate(link);
  };

  return (
    <div className="myGigs">
      <div className="container">
        <div className="title">
          <h1>{currentUser?.traveler ? "Gigs" : "Orders"}</h1>
          {currentUser?.traveler && (
            <Link to="/add">
              <button>Add New Gig</button>
            </Link>
          )}
        </div>
        <table>
          <tr>
            <th>Image</th>
            <th>Title</th>
            <th>Price</th>
            <th>Date</th>
            <th>Action</th>
          </tr>
          {offers.map((offer) => (
            <tr key={offer.id}>
              <td>
                <img className="image" src={`${offer.image}`} alt="" />
              </td>
              <td>{offer.title}</td>
              <td>
                {offer.price}
                <sup>TND</sup>
              </td>
              <td>{new Date(offer.date).toUTCString()}</td>
              <td>
                <img
                  className="delete"
                  src="./img/delete.png"
                  alt=""
                  onClick={(event) => handleDelete(event, offer.id)}
                />
              </td>
              <td>
                <img
                  className="edit"
                  src="./img/edit.png"
                  alt="edit"
                  onClick={(event) => handelUpdate(event, offer.id)}
                ></img>
              </td>
            </tr>
          ))}
        </table>
      </div>
    </div>
  );
}

export default MyGigs;
