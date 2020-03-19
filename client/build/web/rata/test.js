var ctx = document.getElementById('myChart').getContext('2d');
var test=JSON.parse("{\"haha\":2,\"lol\":[{\"hehe\":\"12\"},{\"hoho\":\"aza\"}]}");
console.log(test);
var marksData = {
    labels: ["Température (°C)" ,"Taux de globlule Rouge","Taux de globule blanc","Sucre","Lipide","Glucide","Taux oxygène sanguin","Tension arterielle","Battement de couer (bpm)", "Poids"],
    datasets: [{
      label: " A",
      backgroundColor: "rgba(200,0,0,0.2)",
      pointHitRadius: 50,
      data: [0, 0,0,0,0, 0,0,0,0,0]
    },
    {
      label: "Student B",
      backgroundColor: "rgba(200,0,0,0.2)",
      pointHitRadius: 25,
      data: [70, 75, 70, 80, 40, 60,50,75,80]
    }
  ]
  };
var chart = new Chart(ctx, {
    // The type of chart we want to create
    type: 'radar',
    // The data for our dataset
    data: marksData,
    options:{
      dragData: true,
      dragDataRound:0,
      onDragEnd: function(e,datasetIndex,index,value){
        console.log(datasetIndex,index,value);

      },
      scale: {
        ticks: {
          max: 100,
          min: 0,
          stepSize: 10
        },
        gridLines: {
                display:false
            }
      },
      dragOptions: {
        magnet: {
          to: Math.round
        }
      }
    }
});

function add() {
 var note = [];
 marksData.labels.forEach(element => {

     var lab = document.getElementById(element).value;
    note.push(lab);
 });
    marksData.datasets.push(
        {
            label:"haja",
            backgroundColor: "rgba(106,187,216,0.2)",
            data: note
        }
    )
 //   chart.data.push(marksData);
    chart.update();
}