//
const xhr = new XMLHttpRequest();
var patient;

function diagnostic(){
  const url1="http://localhost:9191/client";
  xhr.open('GET',url1, true);
  http.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
  xhr.send(null);
  xhr.onreadystatechange =function(){
      if (xhr.readyState == 4) {
        if(xhr.status  == 200) {
        //  console.log(JSON.parse(xhr.responseText));
          setAnalyse(JSON.parse(xhr.responseText));
          patient=(JSON.parse(xhr.responseText))[0];
        }else{
          console.log("Error code " + xhr.status);
        }
      }
  }
}
function getAnalyseByPatient(){
  let onePatient=document.getElementById('patient');
  const url="http://localhost:9191/client/test?patient="+onePatient.value+"&getAnalyse=1";
  xhr.open('GET',url, true);
  xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
  xhr.send(null);
  xhr.onreadystatechange =function(){
    if (xhr.readyState == 4) {
      if(xhr.status  == 200) {
      //  console.log(JSON.parse(xhr.responseText));
        setAnalyse(JSON.parse(xhr.responseText));
        patient=(JSON.parse(xhr.responseText))[0];
      }else{
        console.log("Error code " + xhr.status);
      }
    }
  }

}
document.getElementById('patient').addEventListener('change', function() {
  var analysesDrop = document.getElementById("analysePatient");
  analysesDrop.innerHTML='';
});
function setAnalyse(json){
  let analyses=json[0].analyses;
  console.log(json);
  var analysesDrop = document.getElementById("analysePatient");
    analysesDrop.innerHTML='';
            //Add the Options to the DropDownList.
  for (var i = 0; i < analyses.length; i++) {
    var option = document.createElement("OPTION");

  //Set Customer Name in Text part.
    option.innerHTML = analyses[i].dateAnalyses+"|"+analyses[i]._id;

  //Set CustomerId in Value part.
  option.value = ""+i;

  //Add the Option element to DropDownList.
  analysesDrop.options.add(option);
  }
}
// var ctx = document.getElementById('myChart').getContext('2d');
//
//            var marksData = {
//               labels: ["c1", "c2", "c3", "c4", "c5"],
//               datasets: [
//                   {
//                       label: "",
//                       backgroundColor: "rgba(200,0,0,0.2)",
//                       pointHitRadius: 25,
//                       data: []
//                   }
//               ]
//           };
//
//           function voirAnalyse() {
//               let dropdown = document.getElementById("analysePatient");
//               let idanalysedrop = dropdown.value;
//               marksData.datasets[0].data = [];
//               marksData.datasets[0].label=patient.nom+" "+patient.prenom
//                console.log(idanalysedrop);
//               for (var i = 0; i < patient.analyses[idanalysedrop].taux.length; i++) {
//                   marksData.datasets[0].data.push(patient.analyses[idanalysedrop].taux[i].valeur);
//               }
//               chart.update();
//           }
//
//           var chart = new Chart(ctx, {
//               // The type of chart we want to create
//               type: 'radar',
//               // The data for our dataset
//               data: marksData,
//               options: {
//                   dragData: false,
//                   dragDataRound: 0,
//
//                   onDragEnd: function (e, datasetIndex, index, value) {
//                       console.log(datasetIndex, index, value);
//                   },
//                   scale: {
//                       ticks: {
//                           max: 100,
//                           min: 0,
//                           stepSize: 10
//                       },
//                       gridLines: {
//                           display: false
//                       }
//                   },
//                   responsive:true,
//                   maintainAspectRatio:false,
//                   dragOptions: {
//                       magnet: {
//                           to: Math.round
//                       }
//                   }
//               }
//           });
          // function add() {
          //     var note = [];
          //     marksData.labels.forEach(element => {
          //
          //         var lab = document.getElementById(element).value;
          //         note.push(lab);
          //     });
          //     marksData.datasets.push(
          //             {
          //                 label: "haja",
          //                 backgroundColor: "rgba(106,187,216,0.2)",
          //                 data: note
          //             }
          //     );
          //     //   chart.data.push(marksData);
          //     chart.update();
          // }
