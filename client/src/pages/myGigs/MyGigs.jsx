import React, { useEffect } from "react";
import { Link } from "react-router-dom";
import "./MyGigs.scss";
import { hosts } from "../../const";

function MyGigs() {
  const currentUser = {
    id: 1,
    username: "Anna",
    isSeller: true,
  };
  const [offers, setOffers] = React.useState([]);

  const offersFetching = () => {
    fetch(`${hosts.backend}/api/offers`, {
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
      })
      .then(() => {
        offersFetching();
      });
  };

  return (
    <div className="myGigs">
      <div className="container">
        <div className="title">
          <h1>{currentUser.isSeller ? "Gigs" : "Orders"}</h1>
          {currentUser.isSeller && (
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
                <img
                  className="image"
                  src={`./img/offers/${offer.image}`}
                  alt=""
                />
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
            </tr>
          ))}
        </table>
      </div>
    </div>
  );
}

export default MyGigs;
