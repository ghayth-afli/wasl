import React from "react";
import "./GigCard.scss";
import { Link } from "react-router-dom";

const GigCard = ({ item }) => {
  const imageUrl = './img/offers/' + item.image;
  const profileImgUrl = './img/profiles/' + item.traveler.id + '.jpg';
  const linkTo = '/gig/' + item.id;
  return (
    <Link to={linkTo} className="link" onClick={
      () => {
        console.log(item);
        localStorage.setItem('gig', JSON.stringify(item));
      }
    }>
      <div className="gigCard">
        <img src={imageUrl} alt="" />
        <div className="info">
          <div className="user">
            <img src={profileImgUrl} alt="" />
            <span>{item.traveler.user.username}</span>
          </div>
          <p>{item.description}</p>
          <div className="star">
            <img src="./img/star.png" alt="" />
            <span>{item.star}</span>
          </div>
        </div>
        <hr />
        <div className="detail">
          {/* <img src="./img/heart.png" alt="" /> */}
          <div className="delivery">
            <span>Vers {item.destination}</span>
          </div>
          <div className="price">
            <span>STARTING AT</span>
            <h2>
              TND {item.price}
              <sup>99</sup>
            </h2>
          </div>
        </div>
      </div>
    </Link>
  );
};

export default GigCard;
